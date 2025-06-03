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

    @Operation(summary = "FAQ 전체 조회", description = "FAQ 전체 리스트를 조회하는 API")
    @GetMapping("/faq/list")
    public List<FaqVO> getFaqList(FaqDTO faqDTO, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return faqService.getFaqList(faqDTO);
    }

    @Operation(summary = "FAQ 단간 조회", description = "FAQ ID로 상세 정보를 조회하는 API")
    @GetMapping("/faq/{id}")
    public FaqVO getFaq(@PathVariable Long id, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return faqService.findByFaq(id)
                .orElseThrow(() -> new RuntimeException("해당 FAQ가 존재하지 않습니다."));
    }

    @Operation(summary = "FAQ 신규 등록", description = "FAQ 신규 등록하는 API")
    @PostMapping("/faq/register")
    public void registerFaq(@RequestBody FaqVO faqVO, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String identification = jwtTokenUtil.parseToken(token).get("identification", String.class);

        Long adminId = userService.getUserByIdentification(identification)
                .orElseThrow(() -> new RuntimeException("관리자 정보가 없습니다."))
                .getId();

        faqVO.setUserId(adminId);
        faqService.register(faqVO);
    }

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

    @Operation(summary = "전체 QnA 목록 조회", description = "관리자가 전체 QnA 문의 내역을 확인할 수 있는 API")
    @GetMapping("/qna/list")
    public List<QnaDTO> getAllQnaList(HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return qnaService.getAllQnaListForAdmin();
    }

    @Operation(summary = "단일 QnA 조회", description = "관리자가 튼장 QnA 내용을 확인할 수 있는 API")
    @GetMapping("/qna/{id}")
    public QnaDTO getQna(@PathVariable Long id, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return qnaService.getQna(id).orElseThrow(() -> new RuntimeException("존재하지 않는 QnA입니다."));
    }

    @Operation(summary = "QnA 답변 등록", description = "관리자가 QnA에 대한 답변을 등록할 수 있는 API")
    @PostMapping("/qna/answer")
    public void registerQnaAnswer(@RequestBody QnaAnswerVO qnaAnswerVO, HttpServletRequest request) {
        log.info("받은 ID 값: {}", qnaAnswerVO.getId());
        qnaAnswerVO.setId(null);

        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaAnswerService.register(qnaAnswerVO);
    }

    @Operation(summary = "QnA 답변 수정", description = "관리자가 QnA에 등록된 답변을 수정할 수 있는 API")
    @PutMapping("/qna/answer/{id}")
    public void updateQnaAnswer(@PathVariable Long id, @RequestBody QnaAnswerVO qnaAnswerVO, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaAnswerVO.setId(id);
        qnaAnswerService.modify(qnaAnswerVO);
    }

    @Operation(summary = "QnA 삭제", description = "관리자가 QnA 자체를 삭제할 수 있는 API")
    @DeleteMapping("/qna/{id}")
    public void removeQna(@PathVariable Long id, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        qnaService.remove(id);
    }

    @Operation(summary = "회원 전체 리스트", description = "관리자가 모든 회원 정보를 조회하는 API")
    @GetMapping("/user/list")
    public List<UserVO> getUserList(HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        return userService.getUserList();
    }

    @PostMapping("/user/ban")
    public void banUser(@RequestBody List<BanDTO> banList, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }

        for (BanDTO banDTO : banList) {
            int status = banDTO.getUserBanOk();

            // 사유가 없으면 상태에 따라 기본 사유 설정
            if (banDTO.getBanReason() == null || banDTO.getBanReason().isBlank()) {
                switch (status) {
                    case 1 -> banDTO.setBanReason("댓글정지");
                    case 2 -> banDTO.setBanReason("영구정지");
                    default -> banDTO.setBanReason("정지해제");
                }
            }

            // 상태에 따라 서비스 호출
            if (status == 0) {
                // 정지 해제
                userService.updateUserBanStatus(banDTO);
            } else if (status == 1 || status == 2) {
                // 정지 처리
                banDTO.setBanDate(new Timestamp(System.currentTimeMillis()));
                userService.banUser(banDTO);
            } else {
                throw new RuntimeException("잘못된 상태입니다.");
            }
        }
    }

}
