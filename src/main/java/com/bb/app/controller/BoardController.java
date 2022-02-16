package com.bb.app.controller;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.VoteBoardEntity;
import com.bb.app.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    BoardService service;

    Logger logger = LoggerFactory.getLogger(getClass());

    //trade 게시판 컨트롤
    @PostMapping("/trade-board/write")
    public ResponseEntity<Void> TradeboardWrite(@RequestBody BoardEntity board){
        //BoardEntity b = board;
        service.writeBoard(board);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/trade-board/{bno}")
    public Object TradeboardView(@PathVariable long bno){
        Optional<BoardEntity> board;
        board = service.TradeboardView(bno);

        return board;
    }
    @PutMapping("/trade-board/{bno}")
    public Map<String, String> TradeboardUpdate(@PathVariable long bno, @RequestBody BoardEntity board){

        Map<String, String> respones = new HashMap<>();
        Optional<BoardEntity> b = service.TradeboardUpdate(bno);
        logger.info("bno : " + b.get().getId());
        if(b.isPresent()){
            BoardEntity updateBoard = b.get();
            updateBoard.UpdateTile(board.getTitle());
            updateBoard.UpdateContent(board.getContent());
            service.BoardUpdate(updateBoard);
            respones.put("msg","정보변경 성공");
        }else{
            respones.put("msg","정보변경 실패");
        }

        return respones;
    }
    @DeleteMapping("/trade-board/{bno}")
    @Transactional
    public void TradeboardDelete(@PathVariable Long bno){
        service.BoardDelete(bno);
    }
    @GetMapping("/*")
    public Object TradeboardList(){
        List<BoardEntity> board = service.TradeboardList();

        return board;
    }
    //투표게시판 컨트롤러
    @PostMapping("/vote-board/write")
    public ResponseEntity<Void> VoteboardWrite(@RequestBody VoteBoardEntity board){
        String vbContent = board.getTitle();
        logger.info(vbContent);
        service.writeVoteBoard(board);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/vote-board/{bno}")
    public Object VoteboardView(@PathVariable long bno){
        Optional<VoteBoardEntity> vboard = service.VoteboardView(bno);

        return vboard;
    }
    @PutMapping("/vote-board/{bno}")
    public Map<String, String> VoteboardUpdate(@PathVariable long bno, @RequestBody VoteBoardEntity vboard){
        Optional<VoteBoardEntity> vb = service.VoteboardUpdate(bno);
        Map<String, String> response = new HashMap<>();
        
        if(vb.isPresent()){
            VoteBoardEntity updateBoard = vb.get();
            updateBoard.UpdateContent(vboard.getContent());
            updateBoard.UpdateTile(vboard.getTitle());
            
            response.put("msg","수정 성공");
        }else{
            response.put("msg","수정 실패");
        }
        return response;
    }

}
