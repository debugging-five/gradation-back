package com.app.gradationback.mapper.duckjun.util;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.util.AdminCheckUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminCheckUtilTests {

    private AdminCheckUtil adminCheckUtil;
    @Test
    public void adminCheckTest() {
        // 가짜 유저 생성
        UserVO user = new UserVO();
        user.setId(1L);          // ID 1번
        user.setUserAdminOk(true); // 관리자 true

        // 가짜 세션 생성
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        // 관리자 여부 확인
        boolean isAdmin = adminCheckUtil.isAdmin((HttpServletRequest) session);
        log.info("관리자 인증 결과: {}", isAdmin ? "관리자입니다" : "관리자가 아닙니다");
    }
}
