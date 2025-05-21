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
//            HTTP 요청의 Authorization 헤더를 가져옴 : Authorization > 인증받은 사용자 증명 HTTP 헤더
            String authHeader = request.getHeader("Authorization");
            System.out.println("Authorization: " + authHeader);
//            Authorization 헤더가 없거나 Bearer로 시작하지 않으면 false 반환
//            Bearer : 이 토큰을 가진 사람은 인증된 사용자로 간주
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("Authorization 헤더 없음 또는 형식 오류");
                return false;
            }
//            "Bearer " 접두사 제거 후 실제 토큰만 추출
            String token = authHeader.replace("Bearer ", "");
//            토큰이 유효한지 검사 (기간 만료, 위조 여부 등)
            if (!jwtTokenUtil.isTokenValid(token)) {
                System.out.println("토큰 유효하지 않음");
                return false;
            }
//            토큰에서 Claim 정보(속성값들)를 추출
            Claims claims = jwtTokenUtil.parseToken(token);
//            토큰에서 identification(사용자 식별자) 값을 꺼냄
            String identification = claims.get("identification", String.class);
            System.out.println("identification: " + identification);
//            식별자가 null이면 false 반환 (비정상적인 토큰)
            if (identification == null) return false;
//            DB에서 식별자를 기반으로 유저 정보 조회 (없으면 예외 발생)
            UserVO user = userService.getUserByIdentification(identification)
                    .orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));

            System.out.println("관리자 여부: " + user.isUserAdminOk());

            return user.isUserAdminOk();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
