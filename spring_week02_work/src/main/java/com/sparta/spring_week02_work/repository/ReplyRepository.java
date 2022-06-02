package com.sparta.spring_week02_work.repository;

import com.sparta.spring_week02_work.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ReplyRepository extends JpaRepository <Reply, Long> {
    List<Reply> findAllByOrderByModifiedAtDesc();
}
