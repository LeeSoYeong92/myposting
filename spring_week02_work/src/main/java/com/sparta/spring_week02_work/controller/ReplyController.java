package com.sparta.spring_week02_work.controller;

import com.sparta.spring_week02_work.Security.UserDetailsImpl;
import com.sparta.spring_week02_work.dto.ReplyRequestDto;
import com.sparta.spring_week02_work.model.Reply;
import com.sparta.spring_week02_work.repository.ReplyRepository;
import com.sparta.spring_week02_work.service.ReplyService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@ResponseBody


public class ReplyController {
    private final ReplyRepository replyRepository;
    private final ReplyService replyService;

    @GetMapping("/visit/reply")
    public List<Reply> getReply() {
        return replyRepository.findAllByOrderByModifiedAtDesc();
    }


    @PostMapping("/api/reply")
    public Reply createReply(@RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getPerson().getId();
        Reply reply = replyService.createReply(requestDto, userId);
        return reply;
    }

    @PutMapping("/api/reply/{id}")
    public Long updateReply(@PathVariable Long id, @RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getPerson().getId();
        Long real_userId = replyRepository.findById(id).get().getUserId();
        if(userId.equals(real_userId)) {
            replyService.update(id,requestDto);
            return id;
        }else{
            return -1L;
        }}


    @DeleteMapping("/api/reply/{id}")
    public Long deleteReply(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getPerson().getId();
        Long real_userId = replyRepository.findById(id).get().getUserId();
        if(userId.equals(real_userId)) {
            replyRepository.deleteById(id);
            return id;
        }else{
            return -1L;
        }}
}


