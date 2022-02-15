package com.bb.app.controller;

import com.bb.app.entity.BoardEntity;
import com.bb.app.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    BoardService service;

    @PostMapping("/trade-board/write")
    public ResponseEntity<Void> TradeboardWrite(@RequestBody BoardEntity board){
        //BoardEntity b = board;
        service.writeBoard(board);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
