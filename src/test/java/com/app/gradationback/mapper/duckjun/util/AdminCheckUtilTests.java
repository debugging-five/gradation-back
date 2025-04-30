package com.app.gradationback.mapper.duckjun.util;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.util.AdminCheckUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

@SpringBootTest
@Slf4j
public class AdminCheckUtilTests {

    @Test
    public void adminCheckUtilTest() {

        UserVO userVO = new UserVO();
        userVO.setId(2L);
        userVO.setUserAdminOk(true);

        HttpSession httpSession = request.getSession()
        httpSession.setAttribute("user", userVO);

        boolean isAdmin = AdminCheckUtil.isAdmin(httpSession);

        log.info("관리자 인증: {}", isAdmin ? "관리자다" : "관리자 아니다");
    }
}

