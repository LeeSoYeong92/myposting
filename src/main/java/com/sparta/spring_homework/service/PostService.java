package com.sparta.spring_homework.service;

import com.sparta.spring_homework.domain.Post;
import com.sparta.spring_homework.domain.PostRepository;
import com.sparta.spring_homework.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
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
        return post.getId();
    }
}
