package com.app.gradationback.controller;

import com.app.gradationback.domain.QnaDTO;
import com.app.gradationback.domain.QnaVO;
import com.app.gradationback.domain.UpcyclingVO;
import com.app.gradationback.service.QnaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/qna/api/*")
public class QnaController {

    public final QnaService qnaService;

    //    QNA 전체조회
    @Operation(summary = "QNA 전체 조회",description = "QNA를 전체 조회 할 수 있는 API")
    @GetMapping("qna-list")
    public List<QnaDTO> getQnas(String userEmail) {
        return qnaService.getQnaList(userEmail);
    }

    //    QNA 1개 조회
    @Operation(summary = "QNA 단일 내용 조회", description = "QNA 단일 내용을 조회할 수 있는 API")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("qna/{id}")
    public QnaDTO getQna(@PathVariable Long id) {
        log.info("{}", id);
        Optional<QnaDTO> foundQna = qnaService.getQna(id);
        if (foundQna.isPresent()) {
            return foundQna.get();
        }
        return new QnaDTO();
    }

    //    QNA 작성
    @Operation(summary = "문의 작성", description = "QNA를 작성할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "QNA 작성 성공")
    @PostMapping(value = "register", consumes = "multipart/form-data")
    public ResponseEntity<QnaDTO> register(
            @RequestPart("info") QnaVO qnaVO,
            @RequestPart(value = "file", required = false) MultipartFile file) {  // <-- required=false 추가
        qnaService.registraction(qnaVO, file);
        return ResponseEntity.ok(qnaService.getQna(qnaVO.getId()).orElse(new QnaDTO()));
    }


    //    QNA 수정
    @Operation(summary = "QNA 수정", description = "QNA를 수정할 수 있는 API")
    @Parameter(
            name = "id",
            description = "QNA 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @PutMapping("qna/{id}")
    public QnaDTO modify(@PathVariable Long id, @RequestBody QnaVO qnaVO){
        qnaVO.setId(id);
        qnaService.modify(qnaVO);
        Optional<QnaDTO> foundQna = qnaService.getQna(id);
        if (foundQna.isPresent()) {
            return foundQna.get();
        }
        return new QnaDTO();
    }

    //    QNA 삭제
    @Operation(summary = "QNA 삭제", description = "QNA를 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "QNA 삭제 성공")
    @Parameter(
            name = "id",
            description = "QNA 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @DeleteMapping("qna/{id}")
    public void remove(@PathVariable Long id) {
        qnaService.remove(id);
    }

}
