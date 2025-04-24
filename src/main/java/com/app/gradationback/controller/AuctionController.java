package com.app.gradationback.controller;

import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionVO;
import com.app.gradationback.repository.AuctionDAO;
import com.app.gradationback.service.AuctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("auction/api/*")
@RequiredArgsConstructor
@Slf4j
public class AuctionController {
    private final AuctionDAO auctionDAO;
    private final AuctionService auctionService;

    @Operation(summary = "경매 등록", description = "경매 등록 API")
    @ApiResponse(responseCode = "200", description = "등록 성공")
    @PostMapping("registration")
    public void registration(AuctionVO auctionVO) {
        log.info(auctionVO.toString());
        auctionService.auctionRegistration(auctionVO);
    }



}
