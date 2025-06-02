package com.app.gradationback.controller;

import com.app.gradationback.aspect.annotation.ExceptionResponse;
import com.app.gradationback.domain.*;
import com.app.gradationback.repository.ArtImgDAO;
import com.app.gradationback.repository.ExhibitionDAO;
import com.app.gradationback.service.ArtImgService;
import com.app.gradationback.service.ArtService;
import com.app.gradationback.service.ExhibitionService;
import com.app.gradationback.service.UserService;
import com.app.gradationback.util.FileSaveUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
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
    private final UserService userService;

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
        List<String> uuids = new ArrayList<>();
        FileSaveUtil fileSave = new FileSaveUtil();
        Optional<ArtVO> foundArt = artService.getArt(artId);
        if (foundArt.isPresent()) {
            artId = foundArt.get().getId();
//            log.info(artId.toString());
            filePath = filePath + "/" + categoryMap.get(foundArt.get().getArtCategory());
        }

        for(MultipartFile file : files) {
            String uuid = UUID.randomUUID().toString();
            uuids.add(uuid);
            ArtImgVO artImgVO = new ArtImgVO();
            artImgVO.setArtImgName(uuid + "_" + file.getOriginalFilename());
            artImgVO.setArtImgPath(filePath);
            artImgVO.setArtId(artId);
            artImgService.register(artImgVO);
//            log.info(artImgVO.toString());

            fileSave.fileSave(file, artImgVO.getArtImgPath(), artImgVO.getArtImgName());

//            썸네일
            if (file.getContentType().startsWith("image")) {
                FileOutputStream out = new FileOutputStream(new File("C:/upload/" + filePath, "t_" + uuid + "_" + file.getOriginalFilename()));
                Thumbnailator.createThumbnail(file.getInputStream(), out, 300, 300);
                out.close();
            }
        }

        response.put("uuids", uuids);
        response.put("message", "정상적으로 업로드가 완료되었습니다.");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "그라데이션 전시회 이미지 업로드", description = "그라데이션 전시회 이미지 파일 저장 API")
    @PostMapping("upload/exhibition/gradation/{id}")
    public ResponseEntity<Map<String, Object>> gradationExhibitionFileUpload(@RequestParam("files")List<MultipartFile> files, @PathVariable("id") Long id) throws IOException {
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
    public ResponseEntity<Map<String, Object>> universityExhibitionFileUpload(@RequestParam("files")List<MultipartFile> files, @PathVariable Long id) throws IOException {
        Map<String, Object> response = new HashMap<>();

        if (files == null || files.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "파일이 존재하지 않습니다."));
        }

        String filePath = "images/exhibition/university";
        String uuid = UUID.randomUUID().toString();
        FileSaveUtil fileSave = new FileSaveUtil();

        for(MultipartFile file : files) {
            UniversityExhibitionDTO universityExhibitionDTO = new UniversityExhibitionDTO();
            universityExhibitionDTO.setUniversityExhibitionImgName(uuid + file.getOriginalFilename());
            universityExhibitionDTO.setUniversityExhibitionImgPath(filePath);
            universityExhibitionDTO.setUniversityExhibitionId(id);

//            log.info("dto : {}", universityExhibitionDTO);

            exhibitionService.registerUniversityImg(universityExhibitionDTO);
            log.info("university image : {}", file.getOriginalFilename());

            // 파일 저장
            fileSave.fileSave(file, universityExhibitionDTO.getUniversityExhibitionImgPath(), universityExhibitionDTO.getUniversityExhibitionImgName());
        }

        response.put("uuid", UUID.randomUUID().toString());
        response.put("message", "정상적으로 업로드가 완료되었습니다.");
        return ResponseEntity.ok(response);
    }

    @ExceptionResponse
    @Operation(summary = "이미지 조회", description = "패스와 이름을 적으면 이미지를 반환해주는 API")
    @GetMapping("get/{fileName}")
    @ResponseBody
    public byte[] display(@PathVariable String fileName, @RequestParam String filePath) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("c:/upload/" + filePath + "/" + fileName));
    }

//    프로필 사진 변경
    @Operation(summary = "프로필 이미지 변경", description = "사용자 프로필 이미지 파일 저장 및 DB 업데이트 API")
    @PostMapping("upload/profile/{userIdentification}")
    public ResponseEntity<Map<String, Object>> updateProfileImage(@RequestParam("file") MultipartFile file, @PathVariable String userIdentification) throws IOException {
        Map<String, Object> response = new HashMap<>();

        if (file == null || file.isEmpty()) {
            response.put("message", "업로드할 파일이 없습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String filePath = "images/user/profile";
        String uuid = UUID.randomUUID().toString();
        String originalFileName = file.getOriginalFilename();
        String savedFileName = uuid + (originalFileName != null ? originalFileName : "");

        FileSaveUtil fileSave = new FileSaveUtil();
        fileSave.fileSave(file, filePath, savedFileName);

        UserVO userVO = new UserVO();
        userVO.setUserIdentification(userIdentification);
        userVO.setUserImgPath(filePath);
        userVO.setUserImgName(savedFileName);

        userService.modifyProfileImg(userVO);

        response.put("message", "프로필 이미지가 정상적으로 변경되었습니다.");
        response.put("fileName", savedFileName);
        response.put("filePath", filePath);

        return ResponseEntity.ok(response);
    }

    //    대학교 인증
    @Operation(summary = "대학교 인증", description = "대학교 학생증 이미지 파일 저장 및 DB 업데이트 API")
    @PostMapping("upload/certification/{id}")
    public ResponseEntity<Map<String, Object>> updateProfileImage(@RequestParam("file") MultipartFile file, @RequestParam("university") String university, @RequestParam("major") String major,@PathVariable Long id) throws IOException {
        Map<String, Object> response = new HashMap<>();

        if (file == null || file.isEmpty()) {
            response.put("message", "업로드할 파일이 없습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String filePath = "images/user/certification";
        String uuid = UUID.randomUUID().toString();
        String originalFileName = file.getOriginalFilename();
        String savedFileName = uuid + (originalFileName != null ? originalFileName : "");

        FileSaveUtil fileSave = new FileSaveUtil();
        fileSave.fileSave(file, filePath, savedFileName);

        UserVO userVO = new UserVO();
        userVO.setId(id);
        userVO.setUserMyUniversity(university);
        userVO.setUserMyMajor(major);
        userVO.setUserMajorImgPath(filePath);
        userVO.setUserMajorImgName(savedFileName);

        userService.modifyUniversityStatus(userVO);


        response.put("message", "학생증이 정상적으로 등록되었습니다.");
        response.put("fileName", savedFileName);
        response.put("filePath", filePath);

        return ResponseEntity.ok(response);
    }



}
