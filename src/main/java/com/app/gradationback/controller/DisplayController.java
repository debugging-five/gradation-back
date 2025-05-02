//package com.app.gradationback.controller;
//
//import com.app.gradationback.domain.ArtPostDTO;
//import com.app.gradationback.service.DisplayService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//@Slf4j
//@RequiredArgsConstructor
//@RequestMapping("/display/api/*")
//public class DisplayController {
//
//    private final DisplayService displayService;
//    private final ArtPostDTO artPostDTO;
//
//
////    작품 등록
//    @Operation(summary = "작품 등록", description = "작품을 등록할 수 있는 API")
//    @ApiResponse(responseCode = "200", description = "작품 등록 성공")
//    @PostMapping("registration")
//    public ArtPostDTO write(@RequestBody ArtPostDTO artPostDTO) {
//        log.info("Received ArtPostDTO in controller: {}", artPostDTO);
//        displayService.register(artPostDTO);
//
//        Optional<ArtPostDTO> foundArtPost = displayService.getArtPost(artPostDTO.getId());
//        if(foundArtPost.isPresent()) {
//            return foundArtPost.get();
//        }
//        return new ArtPostDTO();
//    }
//
////    작품 수정
//
//
//
//
//}
