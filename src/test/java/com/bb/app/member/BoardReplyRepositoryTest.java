package com.bb.app.member;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.BoardReplyEntity;
import com.bb.app.repository.BoardReplyRepository;
import com.bb.app.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class BoardReplyRepositoryTest {

    @Autowired
    BoardReplyRepository repository;

    @Test
    @Rollback(value = false)
    void test() {

        BoardReplyEntity reply = BoardReplyEntity.builder()
                .comment("댓글입니다4")
                .build();

        repository.save(reply);


    }
}
