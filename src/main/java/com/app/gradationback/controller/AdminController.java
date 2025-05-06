package com.app.gradationback.controller;

import com.app.gradationback.domain.FaqDTO;
import com.app.gradationback.domain.FaqVO;
import com.app.gradationback.domain.QnaDTO;
import com.app.gradationback.domain.QnaAnswerVO;
import com.app.gradationback.service.FaqService;
import com.app.gradationback.service.QnaAnswerService;
import com.app.gradationback.service.QnaService;
import com.app.gradationback.util.AdminCheckUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/api")
public class AdminController {

    private final FaqService faqService;
    private final QnaService qnaService;
    private final QnaAnswerService qnaAnswerService;

    //    관리자용 자주 묻는 질문 목록 조회
    @Operation(summary = "FAQ 전체 조회", description = "FAQ 전체 리스트를 조회하는 API")
    @GetMapping("/list")
    public List<FaqVO> getFaqList(FaqDTO faqDTO, HttpSession session) {
        if(!AdminCheckUtil.isAdmin(session)){
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return faqService.getFaqList(faqDTO);
    }
//  관리자용 자주 묻는 질문 등록
    @Operation(summary = "FAQ 신규 등록", description = "FAQ 신규 등록하는 API")
    @PostMapping("/register")
    public void registerFaq(@RequestBody FaqVO faqVO, HttpSession session) {
        if(!AdminCheckUtil.isAdmin(session)){
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        faqVO.setUserId(1L);
        faqService.register(faqVO);
    }
//  관리자용 자주 묻는 질문 수정
    @Operation(summary = "FAQ 수정", description = "FAQ 내용을 수정하는 API")
    @PutMapping("/modify/{id}")
    public void modifyFaq(@PathVariable Long id, @RequestBody FaqVO faqVO, HttpSession session) {
        if(!AdminCheckUtil.isAdmin(session)){
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        faqVO.setId(id);
        faqService.modify(faqVO);
    }

    @Operation(summary = "FAQ 삭제", description = "FAQ 내용을 삭제하는 API")
    @DeleteMapping("/remove/{id}")
    public void removeFaq(@PathVariable Long id, HttpSession session) {
        if(!AdminCheckUtil.isAdmin(session)){
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        faqService.remove(id);
    }

    // 관리자용 전체 QnA 조회
    @Operation(summary = "전체 QnA 목록 조회", description = "관리자가 전체 QnA 문의 내역을 확인할 수 있는 API")
    @GetMapping("/list")
    public List<QnaDTO> getAllQnaList(HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return qnaService.getAllQnaListForAdmin();
    }

    // 관리자용 단일 QnA 조회
    @Operation(summary = "단일 QnA 조회", description = "관리자가 특정 QnA 내용을 확인할 수 있는 API")
    @GetMapping("/{id}")
    public QnaDTO getQna(@PathVariable Long id, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return qnaService.getQna(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 QnA입니다."));
    }

    // QnA 답변 등록
    @Operation(summary = "QnA 답변 등록", description = "관리자가 QnA에 대한 답변을 등록할 수 있는 API")
    @PostMapping("/answer")
    public void registerQnaAnswer(@RequestBody QnaAnswerVO qnaAnswerVO, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaAnswerService.register(qnaAnswerVO);
    }

    // QnA 답변 수정 (옵션)
    @Operation(summary = "QnA 답변 수정", description = "관리자가 QnA에 등록된 답변을 수정할 수 있는 API")
    @PutMapping("/answer/{id}")
    public void updateQnaAnswer(@PathVariable Long id, @RequestBody QnaAnswerVO qnaAnswerVO, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaAnswerVO.setId(id);
        qnaAnswerService.modify(qnaAnswerVO);
    }

    // QnA 삭제 (관리자가 사용자 문의 삭제 가능)
    @Operation(summary = "QnA 삭제", description = "관리자가 QnA 자체를 삭제할 수 있는 API")
    @DeleteMapping("/{id}")
    public void removeQna(@PathVariable Long id, HttpSession session) {
        if (!AdminCheckUtil.isAdmin(session)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaService.remove(id);
    }
}
