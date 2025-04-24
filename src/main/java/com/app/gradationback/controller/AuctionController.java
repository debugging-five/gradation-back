package com.app.gradationback.controller;

import com.app.gradationback.repository.AuctionDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auction/api/*")
@RequiredArgsConstructor
public class AuctionController {
    private final AuctionDAO auctionDAO;



}
