package com.bb.app.service;

import com.bb.app.entity.BoardEntity;
import com.bb.app.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    BoardRepository repository;

    public void writeBoard(BoardEntity board){
        repository.save(board);
    }
}
