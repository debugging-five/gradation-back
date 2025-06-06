package com.app.gradationback.controller;

import com.app.gradationback.aspect.annotation.ExceptionResponse;
import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionPriceVO;
import com.app.gradationback.domain.AuctionVO;
import com.app.gradationback.exception.AuctionException;
import com.app.gradationback.exception.BiddingException;
import com.app.gradationback.service.AuctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("auction/api/*")
@RequiredArgsConstructor
public class AuctionController {
    private final AuctionService auctionService;

    @ExceptionResponse
    @Operation(summary = "경매 등록", description = "경매 등록 API")
    @ApiResponse(responseCode = "200", description = "등록 성공")
    @PostMapping("registration")
    public ResponseEntity<Map<String, Object>> registration(@RequestBody AuctionVO auctionVO) throws AuctionException {
        Map<String, Object> response = new HashMap<>();
        auctionService.auctionRegistration(auctionVO);
        response.put("message", "등록이 완료되었습니다");
        response.put("request", auctionVO);
        return ResponseEntity.ok(response);
    }

    @ExceptionResponse
    @Operation(summary = "경매 수정", description = "경매 수정 API")
    @ApiResponse(responseCode = "200", description = "수정 성공")
    @PutMapping("modify")
    public ResponseEntity<Map<String, Object>> modify(@RequestBody AuctionVO auctionVO) throws AuctionException {
        Map<String, Object> response = new HashMap<>();
        auctionService.auctionModify(auctionVO);
        response.put("message", "수정이 완료되었습니다");
        response.put("status", auctionVO);
        return ResponseEntity.ok(response);
    }

    @ExceptionResponse
    @Operation(summary = "경매 전체 조회", description = "경매 전체 조회 API")
    @Parameters({
            @Parameter(name = "order", description = "정렬기준", example = "popular"),
            @Parameter(name = "cursor", description = "페이지", example = "1"),
            @Parameter(name = "direction", description = "오름차순", example = "asc"),
            @Parameter(name = "category", description = "분류", example = "건축, 회화, 한국화, 조각, 서예, 공예"),
            @Parameter(name = "status", description = "경매상태", example = "expected, bidding, complete"),
            @Parameter(name = "keyword", description = "검색어", example = "작가이름 or 작품이름")
    })
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @PostMapping("list")
    public ResponseEntity<Map<String, Object>> list(@RequestBody HashMap<String, Object> params) throws AuctionException {

        Map<String, Object> response = new HashMap<>();
        List<AuctionDTO> auctionList = auctionService.readAuctionList(params);
        response.put("auctionList", auctionList);
        response.put("message", "조회완료");
        response.put("params", params);
        if (auctionList.isEmpty()) {
            response.put("contents", 0);
            return ResponseEntity.ok(response);
        }
        response.put("contents" , auctionService.auctionCountList(params));
        return ResponseEntity.ok(response);
    }

    @ExceptionResponse
    @Operation(summary = "경매 정보 조회", description = "경매 1개 정보를 조회할 수 있는 API")
    @Parameter(
            name = "id",
            description = "경매 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("detail/{id}")
    public ResponseEntity<Map<String, Object>> read(@PathVariable Long id) throws AuctionException {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "조회 성공");
        response.put("auction", auctionService.auctionRead(id).orElseThrow(() -> new AuctionException("경매 조회 실패")));
        return ResponseEntity.ok(response);
    }

    @ExceptionResponse
    @Operation(summary = "본인 경매 정보 조회", description = "유저 기준으로 본인의 경매 정보를 조회할 수 있는 API")
    @Parameter(
            name = "userId",
            description = "유저 아이디",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("my-bidding/{userId}")
    public ResponseEntity<Map<String, Object>> readUserBidding(@PathVariable Long userId) throws AuctionException {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "조회 성공");
        response.put("auction", auctionService.auctionFindByUserId(userId));
        return ResponseEntity.ok(response);
    }

    @ExceptionResponse
    @Operation(summary = "하단 경매 조회", description = "페이지 하단의 경매 4개씩을 조회할 수 있는 API")
    @Parameter(
            name = "cursor",
            description = "페이지 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = false
    )
    @GetMapping("footer/{cursor}")
    public ResponseEntity<Map<String, Object>> read(@PathVariable int cursor) throws AuctionException {
        Map<String, Object> response = new HashMap<>();
        List<AuctionDTO> footerList = auctionService.auctionFooterBidding(cursor);
        response.put("footerList", footerList);
        response.put("contents", auctionService.auctionFooterBiddingCount());
        return ResponseEntity.ok(response);
    }


    @ExceptionResponse
    @Operation(summary = "작품 아이디로 경매 조회", description = "작품의 아이디로 경매를 조회할 수 있는 API")
    @Parameter(
            name = "artId",
            description = "작품 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("read-bidding/{artId}")
    public ResponseEntity<Boolean> isExist(@PathVariable Long artId) throws AuctionException {
        Boolean read = auctionService.auctionFindByArtId(artId) != 0;
        return ResponseEntity.ok(read);
    }

    @ExceptionResponse
    @Operation(summary = "경매 삭제", description = "경매를 삭제할 수 있는 API")
    @Parameter(
            name = "id",
            description = "경매 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) throws AuctionException {
        Map<String, Object> response = new HashMap<>();
        auctionService.auctionDelete(id);
        response.put("message", "삭제가 완료되었습니다");
        response.put("status", id);
        return ResponseEntity.ok(response);
    }

//    입찰
    @ExceptionResponse
    @Operation(summary = "경매 응찰", description = "경매 입찰 API")
    @ApiResponse(responseCode = "200", description = "응찰 성공")
    @PostMapping("bidding")
    public ResponseEntity<Map<String,Object>> bidding(@RequestBody AuctionBiddingVO auctionBiddingVO) throws BiddingException {
        Map<String, Object> response = new HashMap<>();
        auctionService.auctionBidding(auctionBiddingVO);
        response.put("message", "응찰 성공");
        response.put("status", auctionBiddingVO);
        return ResponseEntity.ok(response);
    }

    @ExceptionResponse
    @Operation(summary = "경쟁응찰자 조회", description = "해당 경매의 응찰자 수를 조회할 수 있는 API")
    @Parameter(
            name = "auctionId",
            description = "경매 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("read-bidder-count/{auctionId}")
    public ResponseEntity<Map<String, Object>> readBidderCount(@PathVariable Long auctionId) throws BiddingException {
        Map<String, Object> response = new HashMap<>();
        int count = auctionService.auctionBidderCount(auctionId).orElse(0);
        response.put("message", "조회 성공");
        response.put("count", count);
        return ResponseEntity.ok(response);
    }

    @ExceptionResponse
    @Operation(summary = "입찰자 조회", description = "현재 입찰자를 조회할 수 있는 API")
    @Parameter(
            name = "auctionId",
            description = "경매 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("read-bidder/{auctionId}")
    public ResponseEntity<AuctionBiddingVO> readBidder(@PathVariable Long auctionId) throws BiddingException {
        AuctionBiddingVO bidding = auctionService.auctionStatus(auctionId).orElseThrow(() -> new BiddingException("입찰 정보를 찾을 수 없습니다."));
        return ResponseEntity.ok(bidding);
    }

    @ExceptionResponse
    @Operation(summary = "최신 입찰 가격 조회", description = "현재 입찰 가격을 조회할 수 있는 API")
    @Parameter(
            name = "auctionId",
            description = "경매 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("getLatestPrice/{auctionId}")
    public ResponseEntity<Map<String, Object>> getLatestPrice(@PathVariable Long auctionId) throws BiddingException {
        Map<String, Object> response = new HashMap<>();
        Optional<AuctionPriceVO> latestPrice = auctionService.getLatestPrice(auctionId);
        AuctionPriceVO price = latestPrice.orElse(new AuctionPriceVO());
        response.put("price", price);
        return ResponseEntity.ok(response);
    }
}
