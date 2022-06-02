package com.sparta.spring_week02_work.service;

import com.sparta.spring_week02_work.dto.PostRequestDto;
import com.sparta.spring_week02_work.model.Post;
import com.sparta.spring_week02_work.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;


    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id가 존재하지 않습니다")
        );
        post.update(requestDto);
        postRepository.save(post);
        return post.getId();
    }

    public Post createPost(PostRequestDto requestDto, Long userId){
        Post post = new Post(requestDto, userId);
        postRepository.save(post);
        return post;
    }
}
