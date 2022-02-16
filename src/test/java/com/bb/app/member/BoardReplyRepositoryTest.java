package com.bb.app.member;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.BoardReplyEntity;
import com.bb.app.entity.MemberEntity;
import com.bb.app.repository.BoardReplyRepository;
import com.bb.app.repository.BoardRepository;
import com.bb.app.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
class BoardReplyRepositoryTest {

    @Autowired
    BoardReplyRepository replyRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;


    @Test
    @Rollback(value = false)
    void test() {

        Optional<BoardEntity> board = boardRepository.findById(1L);
        Optional<MemberEntity> member = memberRepository.findById(1L);

        BoardReplyEntity reply = BoardReplyEntity.builder()
                .comment("댓글입니다용")
                .board(board.get())
                .writer(member.get())
                .build();

        replyRepository.save(reply);


    }
}
