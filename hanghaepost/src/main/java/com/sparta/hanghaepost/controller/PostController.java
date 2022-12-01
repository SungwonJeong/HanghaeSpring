package com.sparta.hanghaepost.controller;

import com.sparta.hanghaepost.dto.PostRequestDto;
import com.sparta.hanghaepost.dto.PostResponseDto;
import com.sparta.hanghaepost.dto.ResponseDto;
import com.sparta.hanghaepost.entity.Post;
import com.sparta.hanghaepost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController  {

    private final PostService postService;

    //게시글 작성 API
    @PostMapping("/write/post")
    public PostResponseDto writePost(@RequestBody PostRequestDto requestDto) {
        return postService.writePost(requestDto);
    }

    //게시글 전체 조회 API
    @GetMapping("/get/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    //선탁한 게시글 조회 API
    @GetMapping("/get/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    //선택한 게시글 수정 API
    @PatchMapping("/update/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    //선택한 게시글 삭제 API
    @DeleteMapping("/delete/post/{id}")
    public ResponseDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.deletePost(id, requestDto);
    }


}
