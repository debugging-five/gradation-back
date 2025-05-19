package com.app.gradationback.util;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.service.UserService;
import com.app.gradationback.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminCheckUtil {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public boolean isAdmin(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            System.out.println("🔐 Authorization: " + authHeader); // ✅ 추가
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("⛔ Authorization 헤더 없음 또는 형식 오류");
                return false;
            }

            String token = authHeader.replace("Bearer ", "");
            if (!jwtTokenUtil.isTokenValid(token)) {
                System.out.println("⛔ 토큰 유효하지 않음");
                return false;
            }

            Claims claims = jwtTokenUtil.parseToken(token);
            String identification = claims.get("identification", String.class);
            System.out.println("🆔 identification: " + identification);

            if (identification == null) return false;

            UserVO user = userService.getUserByIdentification(identification)
                    .orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));

            System.out.println("👤 관리자 여부: " + user.isUserAdminOk());

            return user.isUserAdminOk();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
