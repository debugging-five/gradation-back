package com.app.gradationback.controller;

import com.app.gradationback.service.ArtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/display/api/*")
public class DisplayController {

    private final ArtService artService;






}
