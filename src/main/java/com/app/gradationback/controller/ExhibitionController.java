package com.app.gradationback.controller;

import com.app.gradationback.domain.GradationExhibitionDTO;
import com.app.gradationback.domain.GradationExhibitionImgVO;
import com.app.gradationback.domain.GradationExhibitionVO;
import com.app.gradationback.service.ExhibitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/exhibitions/api/*")
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

//    gradation
//    전시회 정보 불러오기 0
    @Operation(summary = "현재 gradation 전시회 조회", description = "현재 gradation 전시회를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "현재 gradation 전시회 조회 성공")
    @GetMapping("gradation/current")
    public ResponseEntity<Map<String, Object>> getGradation() {
        Optional<GradationExhibitionVO> gradationExhibitionVO = exhibitionService.getGradation();

        if (gradationExhibitionVO.isPresent()) {
            GradationExhibitionVO gradationExhibition = gradationExhibitionVO.get();
            List<GradationExhibitionImgVO> images = exhibitionService.getGradationImgAll(gradationExhibition.getId());

            Map<String, Object> response = new HashMap<>();
            response.put("gradation", gradationExhibition);
            response.put("images", images);

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

//    전시회 등록 0
    @Operation(summary = "전시회 등록", description = "전시회를 등록할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "전시회 등록 성공")
    @PostMapping("register")
    public GradationExhibitionVO registerGradation(@RequestBody GradationExhibitionVO gradationExhibitionVO) {
        exhibitionService.registerGradation(gradationExhibitionVO);
        return gradationExhibitionVO;
    }

//    전시회 장소 이미지 추가
//    @Operation(summary = "전시관 이미지 추가", description = "전시관 이미지를 추가할 수 있는 API")
//    @ApiResponse(responseCode = "200", description = "이미지 추가 성공")
//    @PostMapping("gradation/image")
//    public GradationExhibitionImgVO addGradationImage(@RequestBody GradationExhibitionImgVO gradationExhibitionImgVO) {
//        exhibitionService.registerGradationImage(gradationExhibitionImgVO);
//        return gradationExhibitionImgVO;
//    }


//    전시회 정보 수정 0
    @Operation(summary = "전시회 정보 수정", description = "전시회 정보를 수정할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "전시회 정보 수정 성공")
    @Parameter(
            name = "id",
            description = "전시회 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @PutMapping("modify/{id}")
    public GradationExhibitionVO modifyGradation(@PathVariable Long id, @RequestBody GradationExhibitionVO gradationExhibitionVO) {
        gradationExhibitionVO.setId(id);
        exhibitionService.editGradation(gradationExhibitionVO);
        Optional<GradationExhibitionVO> foundGradation = exhibitionService.getGradation();
        if (foundGradation.isPresent()) {
            return foundGradation.get();
        }
        return new GradationExhibitionVO();
    }

//    전시회 장소 이미지 삭제 0
    @Operation(summary = "전시관 이미지 삭제", description = "전시관 이미지를 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "이미지 삭제 성공")
    @Parameter(
            name = "id",
            description = "전시관 이미지 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @DeleteMapping("gradation/image/{id}")
    public void deleteGradationImage(@PathVariable("id") Long id) {
        exhibitionService.removeGradationImage(id);
    }

//    최근 전시회 3개 조회
    @Operation(summary = "최근 전시회 3개 조회", description = "최근 전시회 3개를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "최근 전시회 조회 성공")
    @GetMapping("gradation/recent")
    public ResponseEntity<List<String>> getRecentGradation() {
        List<GradationExhibitionVO> exhibitions = exhibitionService.getRecentGradations();

        List<String> exhibitionList = exhibitions
                .stream()
                .map(exhibition -> {
                    String exhibitionDate = exhibition.getGradationExhibitionDate().substring(0, 4);
                    return exhibitionDate + " " + exhibition.getGradationExhibitionTitle();
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(exhibitionList);

    }
}
























