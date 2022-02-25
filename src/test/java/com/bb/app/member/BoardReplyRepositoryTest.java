package com.bb.app.member;

import com.bb.app.DTO.ResBoardReplyDto;
import com.bb.app.Mapper.ResBoardReplyMapper;
import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.BoardReplyEntity;
import com.bb.app.entity.MemberEntity;
import com.bb.app.repository.BoardReplyRepository;
import com.bb.app.repository.BoardRepository;
import com.bb.app.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@Slf4j
class BoardReplyRepositoryTest {

    @Autowired
    BoardReplyRepository replyRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ResBoardReplyMapper resBoardReplyMapper;


    @Test
    @Transactional
    @Rollback(value = false)
    void selectAllByBoardNoTest() {
        List<BoardReplyEntity> boardReplys = replyRepository.selectAllByBoardNo(2L);

        for(BoardReplyEntity boardReply : boardReplys) {
            ResBoardReplyDto replyDto = resBoardReplyMapper.toDto(boardReply);

            log.info("comment : {} ", replyDto.getComment());
            log.info("Id : {} ", replyDto.getId());
            log.info("boardReplyId : {} ", replyDto.getId());
        }
    }
}
