package com.bb.app.controller;

import com.bb.app.DTO.BoardDto;
import com.bb.app.DTO.VoteBoardDto;
import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.VoteBoardEntity;
import com.bb.app.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/boards")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    //trade 게시판 컨트롤
    @PostMapping(value = "/trade-board/write")
    public ResponseEntity<Void> TradeboardWrite(@RequestPart(required = false) List<MultipartFile> files, @RequestPart BoardDto board){
        try{
            log.info("id : {}", board.getMember());
            log.info("content : {}", board.getContent());
        //    log.info("files : {}", files.get(0).getOriginalFilename());
            service.writeBoard(files, board);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/trade-board/{bno}")
    public BoardDto TradeboardView(@PathVariable long bno){
        BoardDto board = service.TradeboardView(bno);
        return board;
    }
    @PutMapping("/trade-board/{bno}")
    public ResponseEntity<Void> TradeboardUpdate(@PathVariable long bno, @RequestBody BoardDto board){
        service.TradeboardUpdate(bno, board);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/trade-board/{bno}")
    public ResponseEntity<Void> TradeboardDelete(@PathVariable Long bno){
        service.BoardDelete(bno);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/trade-board/*")
    public List<BoardDto> TradeboardList(){
        List<BoardDto> board = service.TradeboardList();

        return board;
    }
    //투표게시판 컨트롤러
    @PostMapping("/vote-board/write")
    public ResponseEntity<Void> VoteboardWrite(@RequestBody VoteBoardDto board){
        service.writeVoteBoard(board);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/vote-board/{bno}")
    public VoteBoardDto VoteboardView(@PathVariable long bno){
        VoteBoardDto board = service.VoteboardView(bno);
        return board;
    }
    @PutMapping("/vote-board/{bno}")
    public ResponseEntity<Void> VoteboardUpdate(@PathVariable long bno, @RequestBody VoteBoardDto board){
        service.VoteboardUpdate(bno, board);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/vote-board/{bno}")
    public ResponseEntity<Void> VoteboardDelete(@PathVariable Long bno){
        service.VoteBoardDelete(bno);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/vote-board/*")
    public List<VoteBoardDto> VoteboardList(){
        List<VoteBoardDto> board = service.VoteboardList();

        return board;
    }


}
