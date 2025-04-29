package com.app.gradationback.controller;

import com.app.gradationback.domain.*;
import com.app.gradationback.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mail/api/*")
public class MailController {

    private final MailService mailService;
    private final MailDTO mailDTO;

    // 쪽지 작성
    @Operation(summary = "쪽지 작성", description = "쪽지를 작성할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "쪽지 작성 성공")
    @PostMapping("register")
    public MailDTO register(@RequestBody MailVO mailVO) {
        mailService.register(mailDTO);
        Optional<MailDTO> foundMail = mailService.findSendedOne(mailDTO.getId(), mailDTO.getSendUserId());
        if (foundMail.isPresent()) {
            return foundMail.get();
        }
        return new MailDTO();
    }

}













