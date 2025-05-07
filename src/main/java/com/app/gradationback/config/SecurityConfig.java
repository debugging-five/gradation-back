//package com.app.gradationback.config;
//
//import com.app.gradationback.domain.UserVO;
//import com.app.gradationback.service.UserService;
//import com.app.gradationback.util.JwtTokenUtil;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class SecurityConfig {
//
//    private final UserService userService;
//    private final JwtTokenUtil jwtTokenUtil;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserService userService) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 추가
//                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().permitAll() // 모든 경로 허용
//                )
//                .oauth2Login(oauth -> oauth
//                        .successHandler((request, response, authentication) -> {
//                            if (authentication instanceof OAuth2AuthenticationToken authToken) {
//                                OAuth2User oAuth2User = authToken.getPrincipal();
//                                Map<String, Object> attributes = oAuth2User.getAttributes();
//
//                                String provider = authToken.getAuthorizedClientRegistrationId();
//                                String email = "";
//                                String name = "";
//                                if ("google".equals(provider)) {
//                                    email = (String) attributes.get("email");
//                                    name = (String) attributes.get("name");
//                                } else if ("kakao".equals(provider)) {
//                                    email = ((Map<String, Object>) attributes.get("kakao_account")).get("email").toString();
//                                    name = ((Map<String, Object>) attributes.get("properties")).get("nickname").toString();
//                                } else if ("naver".equals(provider)) {
//                                    email = ((Map<String, Object>) attributes.get("response")).get("email").toString();
//                                    name = ((Map<String, Object>) attributes.get("response")).get("name").toString();
//                                }
//
//                                Map<String, Object> claims = new HashMap<>();
//                                claims.put("email", email);
//                                claims.put("name", name);
//
//                                Long userId = userService.getIdByEmail(email);
//                                String foundUserProvider = userService.getUserByEmail(email).map(UserVO::getUserProvider).orElse(null);
//                                String redirectUrl = "";
//
//                                if (userId != null && !foundUserProvider.equals(provider)) {
//                                    String jwtToken = jwtTokenUtil.generateToken(claims);
//                                    redirectUrl = "http://localhost:3000/?jwtToken=" + jwtToken;
//
//                                } else if (userId != null && !foundUserProvider.equals(provider)) {
//                                    if(foundUserProvider.equals("자사로그인")){
//                                    String jwtToken = jwtTokenUtil.generateToken(claims);
//                                    userService.getUserByEmail(email).ifPresent(user -> {
//                                        UserVO userVO = new UserVO();
//                                        userVO.setId(user.getId());
//                                        userVO.setUserImgName(user.getUserImgName());
//                                        userVO.setUserImgPath(user.getUserImgPath());
//                                        userVO.setUserName(user.getUserName());
//                                        userVO.setUserEmail(user.getUserEmail());
//                                        userVO.setUserIdentification(user.getUserIdentification());
//                                        userVO.setUserPassword(user.getUserPassword());
//                                        userVO.setUserPhone(user.getUserPhone());
//                                        userVO.setUserNickName(user.getUserNickName());
//                                        userVO.setUserProvider(user.getUserProvider());
//                                        userService.modifyUser(userVO);
//                                    });
//                                    redirectUrl = "http://localhost:3000/?jwtToken=" + jwtToken;
//
//                                    } else {
//                                        redirectUrl = "http://localhost:3000/sign-in?error=true";
//                                    }
//                                } else {
//                                    redirectUrl = "http://localhost:3000/sign-up?provider=" + provider + "&email=" + email;
//                                }
//
//                                response.sendRedirect(redirectUrl);
//                            }
//                        })
//                )
//            .logout(logout -> logout
//            .logoutUrl("/logout")
//            .logoutSuccessUrl("http://localhost:3000/sign-in")
//                        .logoutSuccessHandler((request, response, authentication) -> {
//        HttpSession session = request.getSession(false); // false : 세션이 있으면 가져와라
//        if(session != null){
//            session.invalidate();
//        }
//        response.sendRedirect("http://localhost:3000/sign-in");
//        })
//            );
//
//        return http.build();
//}
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost:3000"); // React 앱 주소
//        configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
//        configuration.addAllowedHeader("*"); // 모든 요청 헤더 허용
//        configuration.setAllowCredentials(true); // 인증 정보 허용
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 적용
//        return source;
//}
//}