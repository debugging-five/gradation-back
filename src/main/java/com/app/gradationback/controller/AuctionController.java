package com.app.gradationback.controller;

import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionVO;
import com.app.gradationback.service.AuctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("auction/api/*")
@RequiredArgsConstructor
@Slf4j
public class AuctionController {
    private final AuctionService auctionService;

    @Operation(summary = "경매 등록", description = "경매 등록 API")
    @ApiResponse(responseCode = "200", description = "등록 성공")
    @PostMapping("registration")
    public void registration(AuctionVO auctionVO) {
        log.info(auctionVO.toString());
        auctionService.auctionRegistration(auctionVO);
    }

    @Operation(summary = "경매 수정", description = "경매 수정 API")
    @ApiResponse(responseCode = "200", description = "수정 성공")
    @PutMapping("modify")
    public void modify(AuctionVO auctionVO) {
        log.info(auctionVO.toString());
        auctionService.auctionModify(auctionVO);
    }

    @Operation(summary = "경매 전체 조회", description = "경매 전체 조회 API")
    @Parameters({
            @Parameter(name = "order", description = "정렬기준", example = "popular"),
            @Parameter(name = "cursor", description = "페이지", example = "1"),
            @Parameter(name = "direction", description = "오름차순", example = "asc"),
            @Parameter(name = "category", description = "분류", example = "건축, 회화, 한국화, 조각, 서예, 공예"),
            @Parameter(name = "status", description = "경매상태", example = "expected, bidding, complete")
    })
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("list")
    public List<AuctionDTO> list(@RequestParam HashMap<String, Object> params) {
        log.info(params.toString());
        log.info("{}",auctionService.auctionList(params));
        return auctionService.auctionList(params);
    }

    @Operation(summary = "경매 정보 조회", description = "경매 1개 정보를 조회할 수 있는 API")
    @Parameter(
            name = "id",
            description = "경매 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("detail/{id}")
    public List<AuctionDTO> read(@PathVariable Long id) {
        return auctionService.auctionRead(id);
    }

    @Operation(summary = "경매 삭제", description = "경매를 삭제할 수 있는 API")
    @Parameter(
            name = "id",
            description = "경매 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        auctionService.auctionDelete(id);
    }

//    입찰
    @Operation(summary = "경매 응찰", description = "경매 입찰 API")
    @ApiResponse(responseCode = "200", description = "응찰 성공")
    @PostMapping("bidding")
    public void bidding(AuctionBiddingVO auctionBiddingVO) {
        auctionService.auctionBidding(auctionBiddingVO);
    }

//    해당 겸매의 경쟁응찰자 조회
    @Operation(summary = "경쟁응찰자 조회", description = "해당 경매의 응찰자 수를 조회할 수 있는 API")
    @Parameter(
            name = "auctionId",
            description = "경매 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("bidding-count/{auctionId}")
    public Integer biddingCount(@PathVariable Long auctionId) {
        return auctionService.auctionBidderCount(auctionId).orElse(0);
    }

//    해당 경매의 최종 응찰자 조회
    @Operation(summary = "입찰자 조회", description = "현재 입찰자를 조회할 수 있는 API")
    @Parameter(
            name = "auctionId",
            description = "경매 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("read-bidder/{auctionId}")
    public AuctionBiddingVO readBidder(@PathVariable Long auctionId) {
        Optional<AuctionBiddingVO> foundBidder = auctionService.auctionStatus(auctionId);
        if(foundBidder.isPresent()) {
            return foundBidder.get();
        }
        return new AuctionBiddingVO();
    }




}
