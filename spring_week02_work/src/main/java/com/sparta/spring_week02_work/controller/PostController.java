package com.sparta.spring_week02_work.controller;


import com.sparta.spring_week02_work.Security.UserDetailsImpl;
import com.sparta.spring_week02_work.dto.PostRequestDto;
import com.sparta.spring_week02_work.model.Post;
import com.sparta.spring_week02_work.repository.PostRepository;
import com.sparta.spring_week02_work.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@ResponseBody


public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/visit/posts")
    public List<Post> getPost() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }


    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getPerson().getId();
        Post post = postService.createPost(requestDto, userId);
        System.out.println("햐");
        return post;
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getPerson().getId();
        Long real_userId = postRepository.findById(id).get().getUserId();
        if(userId.equals(real_userId)) {
            postService.update(id,requestDto);
            return id;
        }else{
            return -1L;
    }}

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getPerson().getId();
        Long real_userId = postRepository.findById(id).get().getUserId();
        if(userId.equals(real_userId)) {
            postRepository.deleteById(id);
            return id;
        }else{
            return -1L;
        }}
    }
