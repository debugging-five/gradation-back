package com.app.gradationback.controller;

import com.app.gradationback.domain.*;
import com.app.gradationback.service.*;
import com.app.gradationback.util.AdminCheckUtil;
import io.swagger.v3.oas.annotations.Operation;
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

    //    관리자용 FAQ 목록 조회
    @Operation(summary = "FAQ 전체 조회", description = "FAQ 전체 리스트를 조회하는 API")
    @GetMapping("/faq/list")
    public List<FaqVO> getFaqList(FaqDTO faqDTO, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return faqService.getFaqList(faqDTO);
    }

    //  관리자용 자주 묻는 질문 등록
    @Operation(summary = "FAQ 신규 등록", description = "FAQ 신규 등록하는 API")
    @PostMapping("/faq/register")
    public void registerFaq(@RequestBody FaqVO faqVO, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        faqVO.setUserId(1L);
        faqService.register(faqVO);
    }

    //  관리자용 자주 묻는 질문 수정
    @Operation(summary = "FAQ 수정", description = "FAQ 내용을 수정하는 API")
    @PutMapping("/faq/modify/{id}")
    public void modifyFaq(@PathVariable Long id, @RequestBody FaqVO faqVO, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        faqVO.setId(id);
        faqService.modify(faqVO);
    }

    @Operation(summary = "FAQ 삭제", description = "FAQ 내용을 삭제하는 API")
    @DeleteMapping("/faq/remove/{id}")
    public void removeFaq(@PathVariable Long id, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        faqService.remove(id);
    }

    // 관리자용 전체 QnA 조회
    @Operation(summary = "전체 QnA 목록 조회", description = "관리자가 전체 QnA 문의 내역을 확인할 수 있는 API")
    @GetMapping("/qna/list")
    public List<QnaDTO> getAllQnaList(HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return qnaService.getAllQnaListForAdmin();
    }

    // 관리자용 단일 QnA 조회
    @Operation(summary = "단일 QnA 조회", description = "관리자가 특정 QnA 내용을 확인할 수 있는 API")
    @GetMapping("/qna/{id}")
    public QnaDTO getQna(@PathVariable Long id, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return qnaService.getQna(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 QnA입니다."));
    }

    // QnA 답변 등록
    @Operation(summary = "QnA 답변 등록", description = "관리자가 QnA에 대한 답변을 등록할 수 있는 API")
    @PostMapping("/qna/answer")
    public void registerQnaAnswer(@RequestBody QnaAnswerVO qnaAnswerVO, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaAnswerService.register(qnaAnswerVO);
    }

    // QnA 답변 수정 (옵션)
    @Operation(summary = "QnA 답변 수정", description = "관리자가 QnA에 등록된 답변을 수정할 수 있는 API")
    @PutMapping("/qna/answer/{id}")
    public void updateQnaAnswer(@PathVariable Long id, @RequestBody QnaAnswerVO qnaAnswerVO, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaAnswerVO.setId(id);
        qnaAnswerService.modify(qnaAnswerVO);
    }

    // QnA 삭제 (관리자가 사용자 문의 삭제 가능)
    @Operation(summary = "QnA 삭제", description = "관리자가 QnA 자체를 삭제할 수 있는 API")
    @DeleteMapping("/qna/{id}")
    public void removeQna(@PathVariable Long id, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaService.remove(id);
    }

    // 승인 대기 작품 전체 조회
    @Operation(summary = "작품 승인 대기 전체 조회", description = "관리자 전용: 작품 승인 대기 중인 목록을 조회하는 API")
    @GetMapping("/pending/art")
    public List<ArtDTO> getPendingArtList(HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return artService.getAllArtPending();
    }
    @Operation(summary = "업사이클링 승인 대기 전체 조회", description = "관리자 전용: 업사이클링 승인 대기 중인 목록을 조회하는 API")
    @GetMapping("/pending/upcycling")
    public List<UpcyclingDTO> getPendingUpcyclingList(HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return upcyclingService.getAllUpcyclingPending();
    }

    // 승인 대기 작품 상세 조회
    @Operation(summary = "작품 승인 대기 상세 조회", description = "관리자 전용: 작품 승인 대기 중인 내역을 상세 조회하는 API")
    @GetMapping("/art/pending/{id}")
    public ArtDTO getPendingArt(@PathVariable Long id, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        Optional<ArtDTO> found = artService.getArtPendingById(id);
        return found.orElseThrow(() -> new RuntimeException("해당 작품을 찾을 수 없습니다."));
    }
    @Operation(summary = "업사이클링 승인 대기 내역 상세 조회", description = "관리자 전용: 업사이클링 승인 대기 내역을 상세 조회하는 API")
    @GetMapping("/pending/art/{id}")
    public UpcyclingDTO getPendingUpcycling(@PathVariable Long id, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        Optional<UpcyclingDTO> found = upcyclingService.getUpcyclingPendingById(id);
        return found.orElseThrow(() -> new RuntimeException("해당 작품을 찾을 수 없습니다."));
    }

    // 승인 또는 반려 처리
    @Operation(summary = "작품 상태 변경 (승인 / 반려)", description = "관리자 전용: 작품을 승인 또는 반려 처리하는 API")
    @PatchMapping("/status/art/{id}")
    public void updateArtStatus(@PathVariable Long id, @RequestBody ArtDTO artDTO, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        artDTO.setId(id);
        artService.updateArtStatus(artDTO);
    }
    @Operation(summary = "업사이클링 상태 변경 (승인 / 반려)", description = "관리자 전용: 업사이클링 신청 승인 또는 반려 처리하는 API")
    @PatchMapping("/status/upcycling/{id}")
    public void updateUpcyclingStatus(@PathVariable Long id, @RequestBody UpcyclingDTO upcyclingDTO, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        upcyclingDTO.setId(id);
        upcyclingService.updateUpcyclingStatus(upcyclingDTO);
    }

    // 회원 정지 처리
    // 0 = 일반회원 1 = 댓글 정지 2 = 영구 정지
    @Operation(summary = "회원 정지 처리", description = "관리자 전용: 회원을 댓글 정지/영구 정지/정지 해제하는 API")
    @PostMapping("/user/ban")
    public void banUser(@RequestBody BanDTO banDTO, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
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
            throw new IllegalArgumentException("잘못된 정지 상태입니다.");
        }
    }

}
