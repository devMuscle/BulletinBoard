package com.bb.app.controller;

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

    @Autowired
    VoteReplyService service;

    @PostMapping("")
    private ResponseEntity<Void> WriteReply(@RequestBody VoteReplyEntity voteReply) {
        service.writeBoardReply(voteReply);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{commentNo}")
    private VoteReplyEntity UpdateReply(@PathVariable("commentNo") long no, @RequestBody VoteReplyEntity newVoteReply) {
        VoteReplyEntity voteReply = service.FindCommentById(no);
        String newComment = newVoteReply.getComment();
        voteReply.UpdateComment(newComment);
        Optional<VoteReplyEntity> updatedVoteReply = service.UpdateBoardReply(voteReply);

        return updatedVoteReply.get();
    }

    @DeleteMapping("/{commentNo}")
    private ResponseEntity<Void> DeleteReply(@PathVariable("commentNo") long no) {
        service.DeleteVoteReply(no);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
