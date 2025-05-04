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
//    @PostMapping("register")
//    public ResponseEntity<String> register(@RequestBody ArtPostDTO artPostDTO) {
//        artPostService.register(artPostDTO);
//        return ResponseEntity.ok("게시글이 성공적으로 등록되었습니다.");
//    }
    @PostMapping("register")
    public ArtPostDTO register(@RequestBody ArtPostDTO artPostDTO) {
        log.info("{}", artPostDTO);
        artPostService.register(artPostDTO);
        Optional<ArtPostDTO> foundArtPost = artPostService.getArtPostById(artPostDTO.getArtPostId());
        if(foundArtPost.isPresent()) {
            return foundArtPost.get();
        }
        return new ArtPostDTO();
    }


//    게시글 전체 조회 + 댓글 전체 조회
//    @GetMapping("list")
//    public ResponseEntity<List<Map<String, Object>>> getAllPosts() {
//        List<ArtPostDTO> posts = artPostService.getArtPostList();
//        List<Map<String, Object>> response = new ArrayList<>();
//
//        for (ArtPostDTO post : posts) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("post", post);
//            map.put("comments", commentService.getAllCommentByPostId(post.getId()));
//            response.add(map);
//        }
//
//        return ResponseEntity.ok(response);
//    }
    @GetMapping("list")
    public List<Map<String, Object>> getAllPosts() {
        List<ArtPostDTO> posts = artPostService.getArtPostList();
//        게시글 + 댓글
        List<Map<String, Object>> response = new ArrayList<>();

//        게시글 1개 + 댓글
        for (ArtPostDTO post : posts) {
            Map<String, Object> postDetail = new HashMap<>();
            postDetail.put("post", post);
            postDetail.put("comments", commentService.getAllCommentByPostId(post.getId()));
            response.add(postDetail);
        }
        return response;
    }

//    게시글 단일 조회
//    @GetMapping("/display/{postId}")
//    public ResponseEntity<Map<String, Object>> getPost(@PathVariable Long postId) {
//        ArtPostDTO post = artPostService.getArtPostById(postId).orElseThrow(() ->
//                new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
//        List<CommentVO> comments = commentService.getAllCommentByPostId(postId);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("post", post);
//        response.put("comments", comments);
//
//        return ResponseEntity.ok(response);
//    }
    @GetMapping("/display/{postId}")
    public Map<String, Object> getPost(@PathVariable Long postId) {
        log.info("{}", postId);
        Optional<ArtPostDTO> foundArtPost = artPostService.getArtPostById(postId);
        Map<String, Object> response = new HashMap<>();

        if(foundArtPost.isPresent()) {
            ArtPostDTO post = foundArtPost.get();
            List<CommentVO> comments = commentService.getAllCommentByPostId(postId);
            response.put("comments", comments);
            response.put("post", post);
        }
        return response;
    }

//    게시글 삭제 (작품 + 이미지 + 댓글 삭제)
    @DeleteMapping("/display/{postId}")
    public void deletePost(@PathVariable Long postId) {
        artPostService.removeById(postId);
    }
}







