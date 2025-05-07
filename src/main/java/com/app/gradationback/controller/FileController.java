package com.app.gradationback.controller;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.domain.GradationExhibitionImgVO;
import com.app.gradationback.repository.ArtImgDAO;
import com.app.gradationback.repository.ExhibitionDAO;
import com.app.gradationback.service.ArtImgService;
import com.app.gradationback.service.ExhibitionService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files/api/*")
@Slf4j
public class FileController {

    private final ArtImgService artImgService;
    private final ExhibitionService exhibitionService;

    @Operation(summary = "작품 이미지 업로드", description = "작품 이미지 파일 저장 API")
    @PostMapping("upload/art/{artId}")
    public ResponseEntity<Map<String, Object>> artFileUpload(@RequestParam("files")List<MultipartFile> files, @RequestParam Long artId) throws IOException {
        Map<String, Object> response = new HashMap<>();
        String filePath = "C:/upload/art";
        String uuid = UUID.randomUUID().toString();
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for(MultipartFile file : files) {
            ArtImgVO artImgVO = new ArtImgVO();
            artImgVO.setArtImgName(uuid + file.getOriginalFilename());
            artImgVO.setArtImgPath(filePath);
            artImgVO.setArtId(artId);
            artImgService.register(artImgVO);

            File dest = new File(filePath + "/" + uuid + file.getOriginalFilename());
            file.transferTo(dest);
        }

        response.put("uuid", uuid);
        response.put("message", "정상적으로 업로드가 완료되었습니다.");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "그라데이션 전시회 이미지 업로드", description = "그라데이션 전시회 이미지 파일 저장 API")
    @PostMapping("upload/exhibition/gradation/{id}")
    public ResponseEntity<Map<String, Object>> gradationExhibitionFileUpload(@RequestParam("files")List<MultipartFile> files, @RequestParam Long id) throws IOException {
        Map<String, Object> response = new HashMap<>();
        String filePath = "C:/upload/exhibition/gradation";
        for(MultipartFile file : files) {
            GradationExhibitionImgVO gradationImgVO = new GradationExhibitionImgVO();
            gradationImgVO.setGradationExhibitionImgName(file.getOriginalFilename());
            gradationImgVO.setGradationExhibitionImgPath(filePath);
            gradationImgVO.setGradationExhibitionId(id);
            exhibitionService.registerGradationImage(gradationImgVO);
            log.info("file : {}", file.getOriginalFilename());

            File dest = new File(filePath + "/" + file.getOriginalFilename());
            file.transferTo(dest);
        }

        response.put("uuid", UUID.randomUUID().toString());
        response.put("message", "정상적으로 업로드가 완료되었습니다.");
        return ResponseEntity.ok(response);
    }
//   대학 ExhibitionService 생기면 추가

    @Operation(summary = "이미지 조회", description = "패스와 이름을 적으면 이미지를 반환해주는 API")
    @GetMapping("get/{fileName}/{filePath}")
    @ResponseBody
    public byte[] display(@PathVariable String fileName, @PathVariable String filePath) throws IOException {
        return FileCopyUtils.copyToByteArray(new File(filePath + "/" + fileName));
    }
}
