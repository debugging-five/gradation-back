package com.app.gradationback.controller;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.domain.GradationExhibitionImgVO;
import com.app.gradationback.domain.UniversityExhibitionDTO;
import com.app.gradationback.repository.ArtImgDAO;
import com.app.gradationback.repository.ExhibitionDAO;
import com.app.gradationback.service.ArtImgService;
import com.app.gradationback.service.ArtService;
import com.app.gradationback.service.ExhibitionService;
import com.app.gradationback.util.FileSaveUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files/api/*")
@Slf4j
public class FileController {

    private final ArtService artService;
    private final ArtImgService artImgService;
    private final ExhibitionService exhibitionService;

    @Operation(summary = "작품 이미지 업로드", description = "작품 이미지 파일 저장 API")
    @PostMapping("upload/art/{artId}")
    public ResponseEntity<Map<String, Object>> artFileUpload(@RequestParam("files")List<MultipartFile> files, @PathVariable Long artId) throws IOException {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> categoryMap = new HashMap<>();
        categoryMap.put("한국화", "korean");
        categoryMap.put("회화", "painting");
        categoryMap.put("건축", "architecture");
        categoryMap.put("조각", "sculpture");
        categoryMap.put("서예", "calligraphy");
        categoryMap.put("공예", "craft");

        String filePath = "images/display/art";
        String uuid = UUID.randomUUID().toString();
        FileSaveUtil fileSave = new FileSaveUtil();
        Optional<ArtVO> foundArt = artService.getArt(artId);
        if (foundArt.isPresent()) {
            filePath = filePath + "/" + categoryMap.get(foundArt.get().getArtCategory());
        }

        for(MultipartFile file : files) {
            ArtImgVO artImgVO = new ArtImgVO();
            artImgVO.setArtImgName(uuid + file.getOriginalFilename());
            artImgVO.setArtImgPath(filePath);
            artImgVO.setArtId(artId);
            artImgService.register(artImgVO);

            fileSave.fileSave(file, artImgVO.getArtImgPath(), artImgVO.getArtImgName());
        }

        response.put("uuid", uuid);
        response.put("message", "정상적으로 업로드가 완료되었습니다.");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "그라데이션 전시회 이미지 업로드", description = "그라데이션 전시회 이미지 파일 저장 API")
    @PostMapping("upload/exhibition/gradation/{id}")
    public ResponseEntity<Map<String, Object>> gradationExhibitionFileUpload(@RequestParam("files")List<MultipartFile> files, @RequestParam Long id) throws IOException {
        Map<String, Object> response = new HashMap<>();
        String filePath = "exhibition/gradation";
        String uuid = UUID.randomUUID().toString();
        FileSaveUtil fileSave = new FileSaveUtil();

        for(MultipartFile file : files) {
            GradationExhibitionImgVO gradationImgVO = new GradationExhibitionImgVO();
            gradationImgVO.setGradationExhibitionImgName(uuid + file.getOriginalFilename());
            gradationImgVO.setGradationExhibitionImgPath(filePath);
            gradationImgVO.setGradationExhibitionId(id);
            exhibitionService.registerGradationImage(gradationImgVO);
            log.info("file : {}", file.getOriginalFilename());

            fileSave.fileSave(file, gradationImgVO.getGradationExhibitionImgPath(), gradationImgVO.getGradationExhibitionImgName());
        }

        response.put("uuid", UUID.randomUUID().toString());
        response.put("message", "정상적으로 업로드가 완료되었습니다.");
        return ResponseEntity.ok(response);
    }
//   대학 ExhibitionService 생기면 추가
    @Operation(summary = "대학교 전시회 이미지 업로드", description = "대학교 전시회 이미지 파일 저장 API")
    @PostMapping("upload/exhibition/university/{id}")
    public ResponseEntity<Map<String, Object>> universityExhibitionFileUpload(@RequestParam("files")List<MultipartFile> files, @PathVariable("id") Long universityExhibitionId) throws IOException {
        Map<String, Object> response = new HashMap<>();
        String filePath = "exhibition/university";
        String uuid = UUID.randomUUID().toString();
        FileSaveUtil fileSave = new FileSaveUtil();

        for(MultipartFile file : files) {
            UniversityExhibitionDTO universityExhibitionDTO = new UniversityExhibitionDTO();
            universityExhibitionDTO.setUniversityExhibitionImgName(uuid + file.getOriginalFilename());
            universityExhibitionDTO.setUniversityExhibitionImgPath(filePath);
            universityExhibitionDTO.setUniversityExhibitionId(universityExhibitionId);

            exhibitionService.registerUniversityImg(universityExhibitionDTO);
            log.info("university image : {}", file.getOriginalFilename());

            // 파일 저장
            fileSave.fileSave(file, universityExhibitionDTO.getUniversityExhibitionImgPath(), universityExhibitionDTO.getUniversityExhibitionImgName());
        }

        response.put("uuid", UUID.randomUUID().toString());
        response.put("message", "정상적으로 업로드가 완료되었습니다.");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "이미지 조회", description = "패스와 이름을 적으면 이미지를 반환해주는 API")
    @GetMapping("get/{fileName}")
    @ResponseBody
    public byte[] display(@PathVariable String fileName, @RequestParam String filePath) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("c:/upload/" + filePath + "/" + fileName));
    }
}
