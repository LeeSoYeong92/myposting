package com.sparta.spring_week02_work.model;

import com.sparta.spring_week02_work.dto.ReplyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Reply extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;

    public Reply(String content, Long userId) {
        this.content = content;
        this.userId = userId;
    }

    public Reply(ReplyRequestDto requestDto, Long userId) {
        this.userId = userId;
        this.content = requestDto.getContent();
    }

    public void update(ReplyRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}