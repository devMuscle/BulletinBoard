package com.bb.app.service;

import com.bb.app.entity.BoardReplyEntity;
import com.bb.app.repository.BoardReplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardReplyService {
    @Autowired
    BoardReplyRepository repository;

    public void writeBoardReply(BoardReplyEntity boardReply) {
        repository.save(boardReply);
    }

    public BoardReplyEntity findCommentById(long id) {
        Optional<BoardReplyEntity> b = repository.findById(id);
        BoardReplyEntity boardReply = b.get();
        return boardReply;
    }

    public void updateBoardReply(BoardReplyEntity boardReply) {
        repository.save(boardReply);
    }
}
