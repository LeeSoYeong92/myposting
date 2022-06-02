package com.sparta.spring_week02_work.service;



import com.sparta.spring_week02_work.dto.PostRequestDto;
import com.sparta.spring_week02_work.dto.ReplyRequestDto;
import com.sparta.spring_week02_work.model.Post;
import com.sparta.spring_week02_work.model.Reply;
import com.sparta.spring_week02_work.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public Long update(Long id, ReplyRequestDto requestDto) {
        Reply reply = replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id가 존재하지 않습니다")
        );
        reply.update(requestDto);
        replyRepository.save(reply);
        return reply.getId();
    }

    public Reply createReply(ReplyRequestDto requestDto, Long userId){
        Reply reply = new Reply(requestDto, userId);
        replyRepository.save(reply);
        return reply;
    }
}