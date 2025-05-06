package com.app.gradationback.controller;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.service.UserService;
import com.app.gradationback.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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


////    회원 가입
//    @Operation(summary = "회원가입", description = "회원가입을 할 수 있는 API")
//    @ApiResponse(responseCode = "200", description = "회원가입 성공")
//    @PostMapping("join")
////    public UserVO join(@RequestBody UserVO userVO) {
////        userService.join(userVO);
////        log.info("{}", userVO);
////        Optional<UserVO> foundUser = userService.getUserByEmail(userVO.getUserEmail());
////        if (foundUser.isPresent()) {
////            return foundUser.get();
////        }
////        return new UserVO();
////    }
//    public ResponseEntity<Map<String, Object>> join(@RequestBody UserVO userVO) {
//        Map<String, Object> response = new HashMap<>();
//
//        Long userId = userService.getUserI
//    }

//    로그인
    @PostMapping("login")
    public String login(@RequestBody UserVO userVO) {
        return userService.login(userVO);
    }


//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "로그인 성공하였습니다.");
//        return response


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
    public UserVO getUser(@PathVariable String userEmail) {
        Optional<UserVO> foundUser = userService.getUserByEmail(userEmail);
        log.info("{}", userEmail);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }
        return new UserVO();
    }

//    아이디 중복 체크
    @Operation(summary = "아이디 중복 검사", description = "아이디 중복 검사를 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "아이디 중복 검사 성공")
    @GetMapping("/check-id/{userIdentification}")
    public boolean checkIdentification(@PathVariable String userIdentification) {
        Optional<UserVO> foundUser = userService.getUserByIdentification(userIdentification);
        if (foundUser.isPresent()) {
            return true;
        }
        return false;
    }

//    회원 정보 수정
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공")
    @PutMapping("modify")
    public void modify(@RequestBody UserVO userVO) {
        userService.modifyUser(userVO);
        log.info("{}", userVO);
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
    public void withdraw(@PathVariable String userEmail) {
        userService.withdraw(userEmail);
    }


}
