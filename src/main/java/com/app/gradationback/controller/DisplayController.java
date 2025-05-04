package com.app.gradationback.controller;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.CommentVO;
import com.app.gradationback.service.ArtPostService;
import com.app.gradationback.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/displays/api/*")
@RequiredArgsConstructor
@Slf4j
public class DisplayController {

    private final ArtPostService artPostService;
    private final CommentService commentService;

//    게시글 등록 (게시글 + 작품 + 이미지)
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody ArtPostDTO artPostDTO) {
        artPostService.register(artPostDTO);
        return ResponseEntity.ok("게시글이 성공적으로 등록되었습니다.");
    }


//    게시글 전체 조회 + 댓글 전체 조회
    @GetMapping("list")
    public ResponseEntity<List<Map<String, Object>>> getAllPosts() {
        List<ArtPostDTO> posts = artPostService.getArtPostList();
        List<Map<String, Object>> response = new ArrayList<>();

        for (ArtPostDTO post : posts) {
            Map<String, Object> map = new HashMap<>();
            map.put("post", post);
            map.put("comments", commentService.getAllCommentByPostId(post.getId()));
            response.add(map);
        }

        return ResponseEntity.ok(response);
    }

//    게시글 단일 조회
    @GetMapping("/display/{postId}")
    public ResponseEntity<Map<String, Object>> getPost(@PathVariable Long postId) {
        ArtPostDTO post = artPostService.getArtPostById(postId).orElseThrow(() ->
                new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        List<CommentVO> comments = commentService.getAllCommentByPostId(postId);

        Map<String, Object> response = new HashMap<>();
        response.put("post", post);
        response.put("comments", comments);

        return ResponseEntity.ok(response);
    }

//    게시글 삭제 (작품 + 이미지 + 댓글 삭제)
    @DeleteMapping("/display/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        artPostService.removeById(postId);
        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }
}







