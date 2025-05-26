package com.app.gradationback.controller;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.service.UserService;
import com.app.gradationback.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users/api/*")
public class UserController {

    private final UserService userService;
    private final UserVO userVO;
    private final JwtTokenUtil jwtTokenUtil;


//    일반 회원가입
    @Operation(summary = "회원가입", description = "회원가입을 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    @PostMapping("join/normal")
    public ResponseEntity<Map<String, Object>> normalJoin(@RequestBody UserVO userVO) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<UserVO> foundUser = userService.getUserByEmail(userVO.getUserEmail());
            if (foundUser.isPresent()) {
                response.put("message", "이미 사용 중인 이메일입니다.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
            if (userVO.getUserNickName() == null || userVO.getUserNickName().length() == 0) {
                userVO.setUserNickName(userVO.getUserName());
            }
            userService.joinNormal(userVO);
            response.put("message", "회원가입이 완료되었습니다.");
            return ResponseEntity.ok(response);

        } catch (DuplicateKeyException e) {
            response.put("message", "이미 사용 중인 이메일입니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


//    소셜 로그인 후 회원가입
    @Operation(summary = "소셜 로그인 후 회원가입", description = "소셜 로그인 후 회원가입을 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    @PostMapping("join/social")
    public ResponseEntity<Map<String, Object>> socialJoin(@RequestBody UserVO userVO) {
        Map<String, Object> response = new HashMap<>();
        String provider = userVO.getUserProvider();

//        회원가입 후 다시 로그인
        try {
            Optional<UserVO> userEmail = userService.getUserByEmail(userVO.getUserEmail());
            if (userEmail.isPresent()) {
                response.put("message", "이미 사용 중인 이메일입니다.");
                response.put("provider", provider);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
//        소셜 로그인 후 회원가입
            userService.joinSocial(userVO);
            response.put("message", "회원가입이 완료되었습니다.");
            return ResponseEntity.ok(response);

        } catch (DuplicateKeyException e) {
            response.put("message", "이미 사용 중인 이메일입니다.");
            response.put("provider", provider);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    로그인
    @Operation(summary = "로그인", description = "로그인을 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserVO userVO, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> claims = new HashMap<>();

        try {
            Optional<UserVO> userIdentification = userService.getUserByIdentification(userVO.getUserIdentification());

            if (!userIdentification.isPresent()) {
                response.put("message", "아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            UserVO foundUser = userIdentification.get();

            if(!foundUser.getUserPassword().equals(userVO.getUserPassword())) {
                response.put("message", "아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            session.setAttribute("id", foundUser.getId());
//            System.out.println(session.getAttribute("id").toString());
//            System.out.println(session.getAttribute("id").toString());
//            System.out.println(session.getAttribute("id").toString());
//            System.out.println(session.getAttribute("id").toString());


            claims.put("email", foundUser.getUserEmail());
            claims.put("identification", foundUser.getUserIdentification());

            String jwtToken = jwtTokenUtil.generateToken(claims);
            response.put("jwtToken", jwtToken);
            response.put("message", "로그인 성공하였습니다.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "회원 프로필 조회", description = "JWT 토큰을 통해 현재 로그인한 회원의 정보를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원 프로필 조회 성공")
    @PostMapping("profile")
    public ResponseEntity<Map<String, Object>> profile(
            @RequestHeader(value = "Authorization", required = false) String jwtToken
    ){
        Map<String, Object> response = new HashMap<>();
        String token = jwtToken != null ? jwtToken.replace("Bearer ", "") : null;

//        log.info("token : {}", token);

        try {
            if (token != null && jwtTokenUtil.isTokenValid(token)) {

                Claims claims = jwtTokenUtil.parseToken(token);
//                String userIdentification = claims.get("identification").toString();
                String userEmail = claims.get("email").toString();

                Long userId = userService.getIdByEmail(userEmail);
                UserVO foundUser = userService.getUserByEmail(userEmail).orElseThrow(() -> {
                    throw new RuntimeException("회원 정보가 존재하지 않습니다.");
                });

                foundUser.setUserPassword(null);
                response.put("currentUser", foundUser);
                return ResponseEntity.ok(response);
            }
            response.put("message", "토큰이 만료되었습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

        } catch (ExpiredJwtException e) {
            response.put("message", "토큰이 만료되었습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


//    단일 회원 정보 조회
    @Operation(summary = "회원 정보 조회", description = "회원 1명의 정보를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공")
    @Parameter(
            name = "userEmail",
            description = "회원 이메일",
            schema = @Schema(type = "string"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("user/{userEmail}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String userEmail) {
        Map<String, Object> response = new HashMap<>();
//        log.info("{}", userEmail);
        try {
            Optional<UserVO> foundUser = userService.getUserByEmail(userEmail);
            if (foundUser.isPresent()) {
                foundUser.get().setUserPassword(null);
                response.put("message", "회원 정보 조회 성공했습니다.");
                response.put("currentUser", foundUser.get());
                return ResponseEntity.ok(response);
            }
            response.put("message", "회원 정보가 존재하지 않습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


//    아이디 중복 체크
    @Operation(summary = "아이디 중복 검사", description = "아이디 중복 검사를 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "아이디 중복 검사 성공")
    @GetMapping("/check-id/{userIdentification}")
    public ResponseEntity<Map<String, Object>> checkIdentification(@PathVariable String userIdentification) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<UserVO> foundUser = userService.getUserByIdentification(userIdentification);
            if (foundUser.isPresent()) {
                response.put("check-id", true);
                response.put("message", "이미 사용중인 아이디입니다.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
            response.put("check-id", false);
            response.put("message", "사용 가능한 아이디입니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    이메일 중복 체크
    @Operation(summary = "이메일 중복 검사", description = "이메일 중복 검사를 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "이메일 중복 검사 성공")
    @GetMapping("/check-email/{userEmail}")
    public ResponseEntity<Map<String, Object>> checkEmail(@PathVariable String userEmail) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<UserVO> foundUser = userService.getUserByEmail(userEmail);
            if (foundUser.isPresent()) {
                response.put("check-email", true);
                response.put("message", "이미 사용중인 이메일입니다.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
            response.put("check-email", false);
            response.put("message", "사용 가능한 이메일입니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    아이디 찾기 (이름 + 이메일)
    @Operation(summary = "아이디 찾기", description = "아이디를 찾을 수 있는 API")
    @Parameters({
            @Parameter(name = "userName", description = "회원명", example = "회원명"),
            @Parameter(name = "userEmail", description = "회원 이메일", example = "user@test.app"),
    })
    @ApiResponse(responseCode = "200", description = "아이디 찾기 성공")
    @PostMapping("/find-id")
    public ResponseEntity<Map<String, Object>> findId(@RequestBody UserVO userVO) {
        Map<String, Object> response = new HashMap<>();

        try {
            UserVO foundUser = userService.getIdentificationByEmailAndName(userVO);

            if(foundUser == null) {
                response.put("message", "입력하신 정보에 일치하는 아이디가 존재하지 않습니다.");
                response.put("socialLogin", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else if(foundUser.getUserIdentification() == null) {
                response.put("message", "소셜 로그인 회원입니다.");
                response.put("socialLogin", true);
                return ResponseEntity.ok(response);
            } else {
                response.put("foundIdentification", foundUser.getUserIdentification());
                response.put("message", "아이디 찾기 성공했습니다.");
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    비밀번호 찾기 (이메일) 암호화 필요
    @Operation(summary = "비밀번호 찾기", description = "비밀번호를 찾을 수 있는 API")
    @Parameters({
            @Parameter(name = "userName", description = "회원명", example = "회원명"),
            @Parameter(name = "userEmail", description = "회원 이메일", example = "user@test.app"),
    })
    @ApiResponse(responseCode = "200", description = "비밀번호 찾기 성공")
    @PostMapping("/find-password")
    public ResponseEntity<Map<String, Object>> findPassword(@RequestBody UserVO userVO) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserVO foundUser = userService.getPasswordByEmail(userVO);

            if(foundUser == null) {
                response.put("message", "입력하신 정보에 일치하는 비밀번호가 존재하지 않습니다.");
                response.put("socialLogin", false);
                response.put("status", "fail");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else if(foundUser.getUserIdentification() == null) {
                response.put("message", "소셜 로그인 회원입니다.");
                response.put("socialLogin", true);
                response.put("status", "social");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "비밀번호 찾기 성공했습니다.");
                response.put("socialLogin", false);
                response.put("status", "success");
                response.put("userIdentification", foundUser.getUserIdentification());
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    비밀번호 재설정
    @Operation(summary = "비밀번호 수정", description = "비밀번호를 수정할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "비밀번호 수정 성공")
    @PutMapping("modify-password")
    public ResponseEntity<Map<String, Object>> modifyPassword(@RequestBody UserVO userVO) {
        Map<String, Object> response = new HashMap<>();
        try {
//            Optional<UserVO> foundUser = userService.getUserByIdentification(userVO.getUserIdentification());
            userService.modifyPassword(userVO);
            response.put("message", "비밀번호가 수정되었습니다.");
            response.put("status", "success");
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            response.put("message", "기존 비밀번호와 일치하는 비밀번호는 사용할 수 없습니다.");
            response.put("status", "fail");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    회원 정보 수정
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공")
    @PutMapping("modify")
    public ResponseEntity<Map<String, Object>> modify(@RequestBody UserVO userVO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<UserVO> foundUser = userService.getUserByIdentification(userVO.getUserIdentification());

            if(foundUser.isPresent()) {
                userService.modifyUser(userVO);

                Optional<UserVO> modifyUser = userService.getUserByIdentification(userVO.getUserIdentification());
                if(modifyUser.isPresent()) {
                    UserVO user = modifyUser.get();
                    user.setUserPassword(null);
                    response.put("modifyUser", user);
                    response.put("message", "회원 정보 수정이 완료되었습니다.");
                    return ResponseEntity.ok(response);
                }
            }
            response.put("message", "회원 정보가 존재하지 않습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    회원 프로필 이미지 수정
    @Operation(summary = "회원 프로필 이미지 수정", description = "회원 프로필 이미지를 수정할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원 프로필 이미지 수정 성공")
    @PutMapping("modify/profile-img")
    public ResponseEntity<Map<String, Object>> modifyProfileImg(@RequestBody  UserVO userVO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<UserVO> foundUser = userService.getUserByIdentification(userVO.getUserIdentification());

            if(foundUser.isPresent()) {
                userService.modifyProfileImg(userVO);

                Optional<UserVO> modifyUser = userService.getUserByIdentification(userVO.getUserIdentification());
                if(modifyUser.isPresent()) {
                    UserVO user = modifyUser.get();
                    user.setUserPassword(null);
                    response.put("modifyUser", user);
                    response.put("message", "회원 프로필 이미지 수정이 완료되었습니다.");
                    return ResponseEntity.ok(response);
                }
            }
            response.put("message", "회원 정보가 존재하지 않습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

//    회원 탈퇴
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공")
    @DeleteMapping("withdraw/{userEmail}")
    @Parameter(
            name = "userEmail",
            description = "회원 이메일",
            schema = @Schema(type = "string"),
            in = ParameterIn.PATH,
            required = true
    )
    public ResponseEntity<Map<String, Object>> withdraw(@PathVariable String userEmail) {
        Map<String, Object> response = new HashMap<>();
//        userService.withdraw(userEmail);

        try {
            Optional<UserVO> foundUser = userService.getUserByEmail(userEmail);
            if(foundUser.isPresent()) {
                userService.withdraw(userEmail);
                response.put("message", "회원 탈퇴되었습니다.");
                return ResponseEntity.ok(response);
            }
            response.put("message", "해당 회원이 존재하지 않습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
