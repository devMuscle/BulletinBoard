package com.bb.app.controller;

import com.bb.app.entity.BoardReplyEntity;
import com.bb.app.service.BoardReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments/trade-comment")
public class BoardReplyController {

    @Autowired
    BoardReplyService service;

    @PostMapping("")
    private ResponseEntity<Void> writeReply(@RequestBody BoardReplyEntity boardReply) {
        service.writeBoardReply(boardReply);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{commentNo}")
    private BoardReplyEntity updateReply(@PathVariable("commentNo") long no, @RequestBody BoardReplyEntity newBoardReply) {
        BoardReplyEntity boardReply = service.findCommentById(no);
        String newComment = newBoardReply.getComment();
        boardReply.updateComment(newComment);
        service.updateBoardReply(boardReply);
        return boardReply;
    }
}
