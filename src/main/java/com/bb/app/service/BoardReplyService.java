package com.bb.app.service;

import com.bb.app.DTO.SaveBoardReplyDto;
import com.bb.app.Mapper.SaveBoardReplyMapper;
import com.bb.app.entity.BoardReplyEntity;
import com.bb.app.repository.BoardReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardReplyService {

    private final BoardReplyRepository boardReplyRepository;
    private final SaveBoardReplyMapper saveBoardReplyMapper;

    public void writeBoardReply(SaveBoardReplyDto boardReplyDto) {
        BoardReplyEntity boardReplyEntity = saveBoardReplyMapper.toEntity(boardReplyDto);
        boardReplyRepository.save(boardReplyEntity);
    }

    public void UpdateBoardReply(long no, SaveBoardReplyDto boardReplyDto) {
       Optional<BoardReplyEntity> opBoardReplyEntity = boardReplyRepository.findById(no);
       BoardReplyEntity boardReplyEntity = opBoardReplyEntity.orElseThrow();
       boardReplyEntity.updateComment(boardReplyDto.getComment());
    }

    public void DeleteBoardReply(long replyNo) throws EmptyResultDataAccessException {
        boardReplyRepository.deleteById(replyNo);
    }
}
