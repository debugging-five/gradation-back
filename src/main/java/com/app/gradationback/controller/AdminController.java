package com.app.gradationback.controller;

import com.app.gradationback.domain.FaqDTO;
import com.app.gradationback.domain.FaqVO;
import com.app.gradationback.service.FaqService;
import com.app.gradationback.util.AdminCheckUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/api/faq")
public class AdminController {

    private final FaqService faqService;

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
}
