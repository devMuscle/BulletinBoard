package com.bb.app.member;

import com.bb.app.entity.BoardEntity;
import com.bb.app.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository repository;

    @Test
    @Transactional
    @Rollback(value = false)
    void test() {
        BoardEntity b = BoardEntity.builder()
                .id(4L)
                .title("test")
                .build();

        repository.save(b);


    }
}
