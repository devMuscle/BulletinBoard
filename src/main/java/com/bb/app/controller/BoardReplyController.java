package com.bb.app.controller;

import com.bb.app.entity.BoardReplyEntity;
import com.bb.app.service.BoardReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/comments/trade-comment")
public class BoardReplyController {

    @Autowired
    BoardReplyService service;

    @PostMapping("")
    private ResponseEntity<Void> WriteReply(@RequestBody BoardReplyEntity boardReply) {
        service.writeBoardReply(boardReply);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{commentNo}")
    private BoardReplyEntity UpdateReply(@PathVariable("commentNo") long no, @RequestBody BoardReplyEntity newBoardReply) {
        BoardReplyEntity boardReply = service.FindCommentById(no);
        String newComment = newBoardReply.getComment();
        boardReply.UpdateComment(newComment);
        Optional<BoardReplyEntity> updatedBoardReply = service.UpdateBoardReply(boardReply);

        return updatedBoardReply.get();
    }

    @DeleteMapping("/{commentNo}")
    private ResponseEntity<Void> DeleteReply(@PathVariable("commentNo") long no) {
        service.DeleteBoardReply(no);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
