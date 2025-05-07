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

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/exhibitions/api/*")
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

//    gradation
//    전시회 정보 화면에 불러오기
    @Operation(summary = "현재 gradation 전시회 조회", description = "현재 gradation 전시회를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "현재 gradation 전시회 조회 성공")
    @GetMapping("gradation")
    public List<GradationExhibitionDTO> getGradation() {
        log.info("getGradation 요청 들어옴");
        return exhibitionService.getGradation();
    }

//    전시회 등록
    @Operation(summary = "전시회 등록", description = "전시회를 등록할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "전시회 등록 성공")
    @PostMapping("register")
    public void registerGradation(@RequestBody GradationExhibitionVO gradationExhibitionVO, @RequestBody List<GradationExhibitionImgVO> gradationExhibitionImgVOList) {
        exhibitionService.registerGradation(gradationExhibitionVO, gradationExhibitionImgVOList);
    }


//    전시회 정보 수정
    @Operation(summary = "전시회 정보 수정", description = "전시회 정보를 수정할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "전시회 정보 수정 성공")
    @PutMapping("gradation")
    public void modifyGradation(@RequestBody GradationExhibitionVO gradationExhibitionVO) {
        exhibitionService.editGradation(gradationExhibitionVO);
    }

//    전시회 장소 이미지 추가
    @Operation(summary = "전시관 이미지 추가", description = "전시관 이미지를 추가할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "이미지 추가 성공")
    @PostMapping("gradation/image")
    public void addGradationImage(@RequestBody GradationExhibitionImgVO gradationExhibitionImgVO) {
        exhibitionService.registerGradationImage(gradationExhibitionImgVO);
    }

//    전시회 장소 이미지 삭제
    @Operation(summary = "전시관 이미지 삭제", description = "전시관 이미지를 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "이미지 삭제 성공")
    @DeleteMapping("gradation/image/{id}")
    public void deleteGradationImage(@PathVariable("id") Long id) {
        exhibitionService.removeGradationImage(id);
    }


}
























