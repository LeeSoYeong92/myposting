package com.sparta.spring_week02_work.repository;


import com.sparta.spring_week02_work.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PostRepository extends JpaRepository <Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
}




