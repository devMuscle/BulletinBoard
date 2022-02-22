package com.bb.app.controller;

import com.bb.app.DTO.VoteReplyDto;
import com.bb.app.entity.VoteReplyEntity;
import com.bb.app.service.VoteReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comments/vote-comment")
public class VoteReplyController {

    final VoteReplyService voteReplyService;
    public VoteReplyController(VoteReplyService voteReplyService){
        this.voteReplyService = voteReplyService;
    }

    @PostMapping("")//댓글 작성
    private ResponseEntity<Void> WriteReply(@RequestBody VoteReplyDto reply) {
        voteReplyService.writeBoardReply(reply);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{commentNo}")//댓글 수정
    private ResponseEntity<Void> UpdateReply(@PathVariable long commentNo, @RequestBody VoteReplyDto newVoteReply) {
        voteReplyService.UpdateBoardReply(commentNo, newVoteReply);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentNo}")//댓글 삭제
    private ResponseEntity<Void> DeleteReply(@PathVariable long commentNo) {
        voteReplyService.DeleteVoteReply(commentNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
