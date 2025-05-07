package com.app.oauth.config;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.service.UserService;
import com.app.gradationback.util.JwtTokenUtil;
import com.app.oauth.domain.OauthMemberVO;
import com.app.oauth.service.MemberService;
import com.app.oauth.util.JwtTokenUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserService userService) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 추가
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // 모든 경로 허용
                )
                .oauth2Login(oauth -> oauth
//                                .loginPage("/oauth2/authorization/google")
                                .successHandler((request, response, authentication) -> {
//                            log.info("{}", authentication);
                                    if(authentication instanceof OAuth2AuthenticationToken){
                                        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
                                        OAuth2User user = authToken.getPrincipal();
                                        Map<String, Object> attributes = user.getAttributes();

//                                log.info("oauth2AuthenticationToken {}", authToken);

//                                Oauth2 제공자의 정보(Email, name, provider)
                                        String provider = authToken.getAuthorizedClientRegistrationId(); // ex) "google", "naver", "kakao"
                                        String email = "";
                                        String name = "";
                                        if(provider.equals("google")){
                                            email = (String)attributes.get("email");
                                            name = (String)attributes.get("name");
                                        }else if(provider.equals("kakao")){
                                            email = ((Map<String, Object>)attributes.get("kakao_account")).get("email").toString();
                                            name = ((Map<String, Object>)attributes.get("properties")).get("nickname").toString();
                                        }else if(provider.equals("naver")){
                                            email = ((Map<String, Object>)attributes.get("response")).get("email").toString();
                                            name = ((Map<String, Object>)attributes.get("response")).get("name").toString();
                                        }

                                        log.info("getAttribute : {}", attributes);
//                                        log.info("getAttribute : {}", attributes.get("profile"));
                                        name = (String)attributes.get("name");

                                        Map<String, Object> responseMap = new HashMap<>();
                                        Map<String, Object> claims = new HashMap<>();

                                        claims.put("email", email);
                                        claims.put("name", name);

//                                추가 정보를 받기 위해 provider와 email을 화면으로 넘긴다.
                                        Optional<UserVO> userEmail = userService.getUserByEmail(userEmail);
//                                        값이 있다면 프로바이더 없다면 null
                                        String foundUserProvider = userService.getUserByEmail(userEmail).map(UserVO::getUserProvider).orElse(null);
                                        String redirectUrl = "";
//                                        기존 회원의 로그인
                                        if(memberId != null && foundMemberProvider.equals(provider)){
                                            String jwtToken = jwtTokenUtil.generateToken(claims);
                                            redirectUrl = "http://localhost:3000/?jwtToken=" + jwtToken;

//                                            기존 회원이라면 통합
                                        } else if (memberId != null && !foundMemberProvider.equals(provider)) {
//                                            기존회원이 타사 로그인
                                            if(foundMemberProvider.equals("자사로그인")){
                                                String jwtToken = jwtTokenUtil.generateToken(claims);
                                                userService.getUserByIdentification(userI).ifPresent(member -> {
                                                    UserVO userVO = new OauthMemberVO();
                                                    oauthMemberVO.setId(member.getId());
                                                    oauthMemberVO.setMemberEmail(member.getMemberEmail());
                                                    oauthMemberVO.setMemberName(member.getMemberName());
                                                    oauthMemberVO.setMemberPassword(member.getMemberPassword());
                                                    oauthMemberVO.setMemberPicture(member.getMemberPicture());
                                                    oauthMemberVO.setMemberNickName(member.getMemberNickName());
//                                                    소셜로그인에서 넘어온 provide로 변경
                                                    oauthMemberVO.setMemberProvider(provider);
                                                    memberService.modify(oauthMemberVO);
                                                });
                                                redirectUrl = "http://localhost:3000/?jwtToken=" + jwtToken;
//                                                그냥 바로 통합시키는 방법
//                                                1) 회원 조회
//                                                2) 업데이트 쿼리 실행
//                                                3) 토큰 발급
//                                                4) 로그인 처리
                                            }else{
//                                                타사의 소셜로그인
                                                redirectUrl = "http://localhost:3000/sign-in?provider=" + foundMemberProvider + "&login=false";
                                            }
//                                            아니라면 신규 가입
                                        } else{
                                            redirectUrl = "http://localhost:3000/sign-up?provider=" + provider + "&email=" + email;
                                        }

                                        response.sendRedirect(redirectUrl);
                                    }
                                })
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("http://localhost:3000/sign-in")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession(false); // false : 세션이 있으면 가져와라
                            if(session != null){
                                session.invalidate();
                            }
                            response.sendRedirect("http://localhost:3000/sign-in");
                        })
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // React 앱 주소
        configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        configuration.addAllowedHeader("*"); // 모든 요청 헤더 허용
        configuration.setAllowCredentials(true); // 인증 정보 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 적용
        return source;
    }
}
