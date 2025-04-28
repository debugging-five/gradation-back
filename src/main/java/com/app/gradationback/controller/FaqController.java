package com.app.gradationback.controller;

import com.app.gradationback.domain.FaqDTO;
import com.app.gradationback.domain.FaqVO;
import com.app.gradationback.domain.QnaDTO;
import com.app.gradationback.domain.QnaVO;
import com.app.gradationback.service.FaqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/faq/api/*")
public class FaqController {

    public final FaqService faqService;

    //    FAQ 전체조회
    @Operation(summary = "FAQ 전체 조회",description = "FAQ를 전체 조회 할 수 있는 API")
    @GetMapping("faq-list")
    public List<FaqVO> getFaqs() {
        return faqService.getFaqList();
    }

    //    FAQ 1개 조회
    @Operation(summary = "FAQ 단일 내용 조회", description = "FAQ 단일 내용을 조회할 수 있는 API")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("faq/{id}")
    public FaqVO getFaq(@PathVariable Long id) {
        log.info("{}", id);
        Optional<FaqVO> foundFaq = faqService.getFaq(id);
        if (foundFaq.isPresent()) {
            return foundFaq.get();
        }
        return new FaqVO();
    }


    //    FAQ 작성
    @Operation(summary = "FAQ 작성", description = "FAQ를 작성할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "FAQ 작성 성공")
    @PostMapping("registraction")
    public FaqVO registraction(@RequestBody FaqVO faqVO){
        faqService.registraction(faqVO);
        Optional<FaqVO> foundFaq = faqService.getFaq(faqVO.getId());
        if (foundFaq.isPresent()) {
            return foundFaq.get();
        }
        return new FaqVO();
    }

    //    FAQ 수정
    @Operation(summary = "FAQ 수정", description = "FAQ를 수정할 수 있는 API")
    @Parameter(
            name = "id",
            description = "FAQ 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @PutMapping("faq/{id}")
    public FaqVO modify(@PathVariable Long id, @RequestBody FaqVO faqVO){
        faqVO.setId(id);
        faqService.modify(faqVO);
        Optional<FaqVO> foundFaq = faqService.getFaq(id);
        if (foundFaq.isPresent()) {
            return foundFaq.get();
        }
        return new FaqVO();
    }



    //    FAQ 삭제
    @Operation(summary = "FAQ 삭제", description = "FAQ를 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "FAQ 삭제 성공")
    @Parameter(
            name = "id",
            description = "FAQ 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @DeleteMapping("faq/{id}")
    public void remove(@PathVariable Long id) {
        faqService.remove(id);
    }

}
