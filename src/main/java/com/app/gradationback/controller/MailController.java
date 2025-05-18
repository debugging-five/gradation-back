package com.app.gradationback.controller;

import com.app.gradationback.domain.MailDTO;
import com.app.gradationback.domain.MailVO;
import com.app.gradationback.domain.QnaDTO;
import com.app.gradationback.domain.QnaVO;
import com.app.gradationback.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mail/api/*")
public class MailController {

    private final MailService mailService;

//    쪽지 작성
    @Operation(summary = "쪽지 작성", description = "쪽지를 작성할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "쪽지 작성 성공")
    @PostMapping("registraction")
    public MailDTO registraction(@RequestBody MailDTO mailDTO){

        mailDTO.setSendUserId(mailDTO.getSendUserId());
        mailDTO.setReceiveUserEmail(mailDTO.getReceiveUserEmail());
        mailDTO.setMailTitle(mailDTO.getMailTitle());
        mailDTO.setMailContent(mailDTO.getMailContent());

        mailService.register(mailDTO);

        return new MailDTO();
    }
//    {
//  "id": 0,
//  "receiveUserEmail": "이메일을 입력하세요.",
//  "mailTitle": "제목을 입력하세요.",
//  "mailContent": "내용을 입력하세요.",
//  "sendUserId": 0
//}

//    받은 쪽지 리스트
    @Operation(summary = "받은 쪽지 전체 조회",description = "받은 쪽지를 전체 조회 할 수 있는 API")
    @GetMapping("received-list")
    public List<MailDTO> getReceivedList(Long receiveUserId) {
        return mailService.getReceivedList(receiveUserId);
    }

//    보낸 쪽지 리스트
    @Operation(summary = "보낸 쪽지 전체 조회",description = "보낸 쪽지를 전체 조회 할 수 있는 API")
    @GetMapping("sended-list")
    public List<MailDTO> getSendedList(Long sendUserId) {
        return mailService.getSendedList(sendUserId);
    }

//    받은 쪽지 단일조회
    @Operation(summary = "받은 쪽지 내용 조회", description = "받은 쪽지 내용을 조회할 수 있는 API")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("received/{id}")
    public MailDTO getReceivedOne(@PathVariable Long id,@RequestParam Long receiveUserId) {
        log.info("id: {}, receiveUserId: {}", id, receiveUserId);
        Optional<MailDTO> foundReceivedOne = mailService.findReceivedOne(id, receiveUserId);
        if (foundReceivedOne.isPresent()) {
            return foundReceivedOne.get();
        }
        return new MailDTO();
    }

//    보낸쪽지 단일조회
    @Operation(summary = "보낸 쪽지 내용 조회", description = "보낸 쪽지 내용을 조회할 수 있는 API")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("sended/{id}")
    public MailDTO getSendedOne(@PathVariable Long id,@RequestParam Long sendUserId) {
        log.info("id: {}, sendUserId: {}", id, sendUserId);
        Optional<MailDTO> foundSendedOne = mailService.findSendedOne(id, sendUserId);
        if (foundSendedOne.isPresent()) {
            return foundSendedOne.get();
        }
        return new MailDTO();
    }

//    받은 메시지 삭제
    @Operation(summary = "받은 메일 삭제", description = "받은 메일을 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "받은 메일 삭제 성공")
    @Parameter(
            name = "id",
            description = "메일 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @PatchMapping("mail/{id}/delete-received")
    public void deleteReceivedMail(@PathVariable Long id) {
        mailService.removeReceivedMail(id);
    }

//    보낸 메시지 삭제
    @Operation(summary = "보낸 메일 삭제", description = "보낸 메일을 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "보낸 메일 삭제 성공")
    @Parameter(
            name = "id",
            description = "메일 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @PatchMapping("mail/{id}/delete-sended")
    public void deleteSendedMail(@PathVariable Long id) {
        mailService.removeSendedMail(id);
    }

//    알림리스트
    @Operation(summary = "알림 전체 조회",description = "알림을 전체 조회 할 수 있는 API")
    @GetMapping("alert-list")
    public List<MailDTO> getAlerts(Long receiveUserId) {
    return mailService.getAlertList(receiveUserId);
}

    // 알림상세
    @Operation(summary = "알림 단일 내용 조회", description = "알림 단일 내용을 조회할 수 있는 API")
    @Parameter(
            name = "id",
            description = "알림 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("alert/{id}")
    public MailDTO getAlert(@PathVariable Long id) {
        log.info("id: {}", id);
        Optional<MailDTO> foundAlert = mailService.findByDetail(id);
        return foundAlert.orElseGet(MailDTO::new);
    }

//    읽음처리
    @Operation(summary = "읽음처리", description = "읽음처리 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "읽음처리 성공")
    @Parameter(
            name = "id",
            description = "알림 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @PatchMapping("mail/{id}/readUpdate")
    public void readUpdate(@PathVariable Long id, @RequestParam Long receiveUserId) {
        mailService.readUpdate(id, receiveUserId);
    }

//    읽지않은 메일 갯수 카운트
    @Operation(summary = "읽지 않은 메일 개수", description = "읽지 않은 메일의 개수를 조회할 수 있는 API")
    @GetMapping("notRead-count")
    public int countNotRead(@RequestParam Long receiveUserId) {
        return mailService.countNotRead(receiveUserId);
    }

}
