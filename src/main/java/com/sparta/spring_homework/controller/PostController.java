package com.sparta.spring_homework.controller;

import com.sparta.spring_homework.domain.Post;
import com.sparta.spring_homework.domain.PostRepository;
import com.sparta.spring_homework.domain.PostRequestDto;
import com.sparta.spring_homework.service.PostService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@ResponseBody


public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/api/posts")
    public List<Post> getPost() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/posts/{id}")
    Boolean checkPassword(@PathVariable Long id, @RequestParam("password") String password) {
        try {
            Optional<Post> post = postRepository.findByIdAndPassword(id, password);
            if(post.isPresent()){
                return true;
            }else {
                return false;
            } } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/api/posts")
    public Post cratePost(@RequestBody PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.update(id,requestDto);
        return id;
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }
}