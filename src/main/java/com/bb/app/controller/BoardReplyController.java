package com.bb.app.controller;

import com.bb.app.DTO.SaveBoardReplyDto;
import com.bb.app.entity.BoardReplyEntity;
import com.bb.app.exception.DeleteException;
import com.bb.app.service.BoardReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://ec2-13-209-50-25.ap-northeast-2.compute.amazonaws.com/", allowedHeaders = "*")
@RequestMapping("/comments/trade-comment")
@RequiredArgsConstructor
public class BoardReplyController {

    private final BoardReplyService boardReplyService;

    @PostMapping("")
    private ResponseEntity<Void> WriteReply(@RequestBody SaveBoardReplyDto boardReplyDto) {
        boardReplyService.writeBoardReply(boardReplyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{commentNo}")
    private ResponseEntity<Void> UpdateReply(@PathVariable("commentNo") long no, @RequestBody SaveBoardReplyDto boardReplyDto) {
        try{
            boardReplyService.UpdateBoardReply(no, boardReplyDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{commentNo}")
    private ResponseEntity<Void> DeleteReply(@PathVariable("commentNo") long no) {
        try {
            boardReplyService.DeleteBoardReply(no);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
