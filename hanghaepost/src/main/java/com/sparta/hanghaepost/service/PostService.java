package com.sparta.hanghaepost.service;

import com.sparta.hanghaepost.dto.*;
import com.sparta.hanghaepost.entity.Post;
import com.sparta.hanghaepost.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //게시글 작성
    @Transactional
    public PostResponseDto writePost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    //게시글 전체 조회
    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    //선택한 게시글 조회
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = checkPost(postRepository, id);
        return new PostResponseDto(post);
    }

    //선택한 게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = checkPost(postRepository, id);
        // 비밀번호 확인
        if (post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
            return new PostResponseDto(post);
        }
        return null;
    }

    //선택한 게시글 삭제
    @Transactional
    public ResponseDto deletePost(Long id, PostRequestDto requestDto) {
        Post post = checkPost(postRepository, id);
        // 비밀번호 확인
        if (post.getPassword().equals(requestDto.getPassword())) {
            postRepository.delete(post);
            return new ResponseDto("true", HttpStatus.OK.value());
        }
        return null;
    }

    private Post checkPost(PostRepository postRepository, Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 게시글을 찾을 수 없습니다.")
        );
    }
}
