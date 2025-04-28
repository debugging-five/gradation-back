package com.app.gradationback.controller;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users/api/*")
public class UserController {

    private final UserService userService;
    private final UserVO userVO;

//    회원 가입
    @Operation(summary = "회원가입", description = "회원가입을 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    @PostMapping("join")
    public UserVO join(@RequestBody UserVO userVO) {
        userService.join(userVO);
        log.info("{}", userVO);
        Optional<UserVO> foundUser = userService.getUser(userVO.getUserEmail());
        if (foundUser.isPresent()) {
            return foundUser.get();
        }
        return new UserVO();
    }

//    단일 회원 정보 조회
    @Operation(summary = "회원 정보 조회", description = "회원 1명의 정보를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공")
    @Parameter(
            name = "id",
            description = "회원 이메일",
            schema = @Schema(type = "string"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("user/{id}")
    public UserVO getUser(@PathVariable("id") String userEmail) {
        Optional<UserVO> foundUser = userService.getUser(userEmail);
        log.info("{}", userEmail);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }
        return new UserVO();
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
    @DeleteMapping("withdraw/{id}")
    @Parameter(
            name = "id",
            description = "회원 이메일",
            schema = @Schema(type = "string"),
            in = ParameterIn.PATH,
            required = true
    )
    public void withdraw(@PathVariable("id") String userEmail) {
        userService.withdraw(userEmail);
    }


}
