package com.bb.app.controller;

import com.bb.app.DTO.BoardDetailDto;
import com.bb.app.DTO.BoardDto;
import com.bb.app.DTO.VoteBoardDetailDto;
import com.bb.app.DTO.VoteBoardDto;
import com.bb.app.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/boards")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    //trade 게시판 컨트롤
    @PostMapping(value = "/trade-board/write")
    public ResponseEntity<Void> TradeboardWrite(@RequestPart(required = false) List<MultipartFile> files, @RequestPart BoardDto board){
        try{
            service.writeBoard(files, board);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/trade-board/{bno}")
    public BoardDetailDto TradeboardView(@PathVariable long bno){
        BoardDetailDto board = service.TradeboardView(bno);
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

    @GetMapping("/trade-board")
    public List<BoardDto> TradeboardList(){
        List<BoardDto> board = service.TradeboardList();

        return board;
    }

    //투표게시판 컨트롤러
    @PostMapping("/vote-board/write")
    public ResponseEntity<Void> VoteboardWrite( @RequestPart(required = false) List<MultipartFile> files, @RequestPart VoteBoardDto board) {
        try {
            service.writeVoteBoard(files, board);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vote-board/{bno}")
    public ResponseEntity<VoteBoardDetailDto> VoteboardView(@PathVariable long bno){
        VoteBoardDetailDto board = service.VoteboardView(bno);

        return new ResponseEntity<>(board, HttpStatus.OK);
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
    @GetMapping("/vote-board")
    public List<VoteBoardDto> VoteboardList(){
        List<VoteBoardDto> board = service.VoteboardList();

        return board;
    }
}
