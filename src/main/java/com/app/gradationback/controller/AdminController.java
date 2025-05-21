package com.app.gradationback.controller;

import com.app.gradationback.domain.*;
import com.app.gradationback.service.*;
import com.app.gradationback.util.AdminCheckUtil;
import com.app.gradationback.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/api")
public class AdminController {

    private final FaqService faqService;
    private final QnaService qnaService;
    private final QnaAnswerService qnaAnswerService;
    private final ArtService artService;
    private final UserService userService;
    private final UpcyclingService upcyclingService;
    private final AdminCheckUtil adminCheckUtil;
    private final JwtTokenUtil jwtTokenUtil;

//        테스트용
//    @PostMapping("/admin/test-login")
//    public void testLogin(HttpSession session) {
//      UserVO userVO = new UserVO();
//      userVO.setId(1L);
//      userVO.setUserAdminOk(true);
//      session.setAttribute("user", userVO);
//    }

    //        관리자용 FAQ 목록 조회
    @Operation(summary = "FAQ 전체 조회", description = "FAQ 전체 리스트를 조회하는 API")
    @GetMapping("/faq/list")
//    VO는 뷰용, DTO는 검색 조건 수집(카테고리로 필터나 키워드로 검색)
    public List<FaqVO> getFaqList(FaqDTO faqDTO, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return faqService.getFaqList(faqDTO);
    }

    //        관리자용 FAQ 단일 조회
    @Operation(summary = "FAQ 단건 조회", description = "FAQ ID로 상세 정보를 조회하는 API")
    @GetMapping("/faq/{id}")
    public FaqVO getFaq(@PathVariable Long id, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return faqService.findByFaq(id)
                .orElseThrow(() -> new RuntimeException("해당 FAQ가 존재하지 않습니다."));
    }


    //      관리자용 자주 묻는 질문 등록
    @Operation(summary = "FAQ 신규 등록", description = "FAQ 신규 등록하는 API")
    @PostMapping("/faq/register")
    public void registerFaq(@RequestBody FaqVO faqVO, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
//        http 헤더에서 Authorization 값 꺼내고, Bearer 문자열 제거하면 딱 토큰 값만 나오
        String token = request.getHeader("Authorization").replace("Bearer ", "");
//        토큰 파싱해서 identification값 꺼내기 요게 로그인한 사용자 식별 (이메일이나 이름)
        String identification = jwtTokenUtil.parseToken(token).get("identification", String.class);

//        관리자 ID 조회
        Long adminId = userService.getUserByIdentification(identification)
                .orElseThrow(() -> new RuntimeException("관리자 정보가 없습니다."))
                .getId();

//        관리자 ID 설정. 누가 등록했는지 DB에 남겨려고....
        faqVO.setUserId(adminId);
        faqService.register(faqVO);
    }

    //      관리자용 자주 묻는 질문 수정
    @Operation(summary = "FAQ 수정", description = "FAQ 내용을 수정하는 API")
    @Parameter (name = "id", description = "FAQ 번호", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true)
    @PutMapping("/faq/modify/{id}")
    public FaqVO modifyFaq(@PathVariable Long id, @RequestBody FaqVO faqVO, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }

        faqVO.setId(id);
        faqService.modify(faqVO);

        return faqService.findByFaq(id)
                .orElseThrow(() -> new RuntimeException("수정된 FAQ를 찾을 수 없습니다."));
    }

    @Operation(summary = "FAQ 삭제", description = "FAQ 내용을 삭제하는 API")
    @DeleteMapping("/faq/{id}")
    public void removeFaq(@PathVariable Long id, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        faqService.remove(id);
    }

    //     관리자용 전체 QnA 조회
    @Operation(summary = "전체 QnA 목록 조회", description = "관리자가 전체 QnA 문의 내역을 확인할 수 있는 API")
    @GetMapping("/qna/list")
    public List<QnaDTO> getAllQnaList(HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return qnaService.getAllQnaListForAdmin();
    }

    //     관리자용 단일 QnA 조회
    @Operation(summary = "단일 QnA 조회", description = "관리자가 특정 QnA 내용을 확인할 수 있는 API")
    @GetMapping("/qna/{id}")
    public QnaDTO getQna(@PathVariable Long id, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return qnaService.getQna(id).orElseThrow(() -> new RuntimeException("존재하지 않는 QnA입니다."));
    }

    // QnA 답변 등록
    @Operation(summary = "QnA 답변 등록", description = "관리자가 QnA에 대한 답변을 등록할 수 있는 API")
    @PostMapping("/qna/answer")
    public void registerQnaAnswer(@RequestBody QnaAnswerVO qnaAnswerVO, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaAnswerService.register(qnaAnswerVO);
    }

    // QnA 답변 수정 (옵션)
    @Operation(summary = "QnA 답변 수정", description = "관리자가 QnA에 등록된 답변을 수정할 수 있는 API")
    @PutMapping("/qna/answer/{id}")
    public void updateQnaAnswer(@PathVariable Long id, @RequestBody QnaAnswerVO qnaAnswerVO, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaAnswerVO.setId(id);
        qnaAnswerService.modify(qnaAnswerVO);
    }

    // QnA 삭제 (관리자가 사용자 문의 삭제 가능)
    @Operation(summary = "QnA 삭제", description = "관리자가 QnA 자체를 삭제할 수 있는 API")
    @DeleteMapping("/qna/{id}")
    public void removeQna(@PathVariable Long id, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaService.remove(id);
    }

    // 회원 정지 처리
    // 0 = 일반회원 1 = 댓글 정지 2 = 영구 정지
    @Operation(summary = "회원 정지 처리", description = "관리자 전용: 회원을 댓글 정지/영구 정지/정지 해제하는 API")
    @PostMapping("/user/ban")
    public void banUser(@RequestBody BanDTO banDTO, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }

        int status = banDTO.getUserBanOk();

        if (status == 0) {
            // 정지 해제
            userService.updateUserBanStatus(banDTO);
        } else if (status == 1 || status == 2) {
            // 정지 처리 (댓글 정지 / 영구 정지)
            banDTO.setBanDate(new Timestamp(System.currentTimeMillis())); // 정지 일자 자동 세팅
            userService.banUser(banDTO);
        } else {
            throw new RuntimeException("잘못된 정지 상태입니다.");
        }
    }




}
