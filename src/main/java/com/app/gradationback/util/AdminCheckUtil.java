package com.app.gradationback.util;

import com.app.gradationback.domain.UserVO;
import jakarta.servlet.http.HttpSession;

public class AdminCheckUtil {
//  세션에서 로그인된 유저의 관리자 여부 확인
    public static boolean isAdmin(HttpSession session) {
        UserVO userVO = (UserVO)session.getAttribute("user");
        if (userVO == null) {
            return false;
        }
//  ID가 1이고 관리자 권한이 true면 관리자
        return userVO.getId() == 1L && userVO.isUserAdminOk();
    }
}
