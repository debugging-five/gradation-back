package com.app.gradationback.controller;

import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionPriceVO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("auction/api/*")
@RequiredArgsConstructor
@Slf4j
public class AuctionController {
    private final AuctionService auctionService;

    @Operation(summary = "경매 등록", description = "경매 등록 API")
    @ApiResponse(responseCode = "200", description = "등록 성공")
    @PostMapping("registration")
    public ResponseEntity<Map<String, Object>> registration(@RequestBody AuctionVO auctionVO) {
        Map<String, Object> response = new HashMap<>();
        try {
            auctionService.auctionRegistration(auctionVO);
        } catch (Exception e) {
            response.put("message", "등록 실패" + e.getMessage());
            response.put("status", auctionVO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        response.put("message", "등록이 완료되었습니다");
        response.put("status", auctionVO);
        return ResponseEntity.ok(response);

    }

    @Operation(summary = "경매 수정", description = "경매 수정 API")
    @ApiResponse(responseCode = "200", description = "수정 성공")
    @PutMapping("modify")
    public ResponseEntity<Map<String, Object>> modify(@RequestBody AuctionVO auctionVO) {
        Map<String, Object> response = new HashMap<>();
        try {
            auctionService.auctionModify(auctionVO);
        } catch (Exception e) {
            response.put("message", "수정 실패" + e.getMessage());
            response.put("status", auctionVO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        response.put("message", "수정이 완료되었습니다");
        response.put("status", auctionVO);
        return ResponseEntity.ok(response);
    }

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
    @GetMapping("list")
    public ResponseEntity<List<AuctionDTO>> list(@RequestParam HashMap<String, Object> params) {
        return ResponseEntity.ok(auctionService.auctionList(params));
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
    public ResponseEntity<Map<String, Object>> read(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "조회 성공");
        response.put("auction", auctionService.auctionRead(id));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "본인 경매 정보 조회", description = "유저 기준으로 본인의 경매 정보를 조회할 수 있는 API")
    @Parameter(
            name = "userId",
            description = "유저 아이디",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("my-bidding/{userId}")
    public ResponseEntity<Map<String, Object>> readUserBidding(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "조회 성공");
        response.put("auction", auctionService.auctionFindByUserId(userId));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "하단 경매 조회", description = "페이지 하단의 경매 4개씩을 조회할 수 있는 API")
    @Parameter(
            name = "cursor",
            description = "페이지 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = false
    )
    @GetMapping("footer/{cursor}")
    public ResponseEntity<List<AuctionDTO>> read(@PathVariable int cursor) {
        List<AuctionDTO> footerList = null;
        try {
            if (cursor == 0) {
                footerList = auctionService.auctionFooterBidding(1);
            }
            footerList = auctionService.auctionFooterBidding(cursor);
            return ResponseEntity.ok(footerList);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(footerList);
        }
    }



    @Operation(summary = "작품 아이디로 경매 조회", description = "작품의 아이디로 경매를 조회할 수 있는 API")
    @Parameter(
            name = "artId",
            description = "작품 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("read-bidding/{artId}")
    public ResponseEntity<Boolean> isExist(@PathVariable Long artId) {
        Boolean read = auctionService.auctionFindByArtId(artId) != 0;
        return ResponseEntity.ok(read);
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
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            auctionService.auctionDelete(id);
        } catch (Exception e) {
            response.put("message", "삭제 실패" + e.getMessage());
            response.put("status", id);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        response.put("message", "삭제가 완료되었습니다");
        response.put("status", id);
        return ResponseEntity.ok(response);
    }

//    입찰
    @Operation(summary = "경매 응찰", description = "경매 입찰 API")
    @ApiResponse(responseCode = "200", description = "응찰 성공")
    @PostMapping("bidding")
    public ResponseEntity<Map<String,Object>> bidding(@RequestBody AuctionBiddingVO auctionBiddingVO) {
        Map<String, Object> response = new HashMap<>();
        try {
            auctionService.auctionBidding(auctionBiddingVO);
            response.put("message", "응찰 성공");
            response.put("status", auctionBiddingVO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("status", auctionBiddingVO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

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
    @GetMapping("read-bidder-count/{auctionId}")
    public ResponseEntity<Map<String, Object>> readBidderCount(@PathVariable Long auctionId) {
        Map<String, Object> response = new HashMap<>();
        int count = auctionService.auctionBidderCount(auctionId).orElse(0);
        response.put("message", "조회 성공");
        response.put("count", count);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "입찰자 조회", description = "현재 입찰자를 조회할 수 있는 API")
    @Parameter(
            name = "auctionId",
            description = "경매 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("read-bidder/{auctionId}")
    public ResponseEntity<AuctionBiddingVO> readBidder(@PathVariable Long auctionId) {
        Optional<AuctionBiddingVO> foundBidder = auctionService.auctionStatus(auctionId);
        if(foundBidder.isPresent()) {
            AuctionBiddingVO bidding = foundBidder.get();
            return ResponseEntity.ok(bidding);
        }
        return ResponseEntity.ok(new AuctionBiddingVO());
    }

    @Operation(summary = "최신 입찰 가격 조회", description = "현재 입찰 가격을 조회할 수 있는 API")
    @Parameter(
            name = "auctionId",
            description = "경매 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("getLatestPrice/{auctionId}")
    public ResponseEntity<Map<String, Object>> getLatestPrice(@PathVariable Long auctionId) {
        Map<String, Object> response = new HashMap<>();
        Optional<AuctionPriceVO> latestPrice = auctionService.getLatestPrice(auctionId);
        if(latestPrice.isPresent()) {
            AuctionPriceVO price = latestPrice.get();
            response.put("price", price);
            return ResponseEntity.ok(response);
        }

//        첫 경매일 때 빈 객체
        response.put("price", new AuctionPriceVO());
        return ResponseEntity.ok(response);
    }


}
