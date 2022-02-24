package com.bb.app.member;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.MemberEntity;
import com.bb.app.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@Slf4j
class BoardRepositoryTest {


    @Autowired
    BoardRepository repository;

    @Test
    @Transactional
    @Rollback(value = false)
    void NoneAttachBoardTest() {
        Optional<BoardEntity> opBoard = repository.findById(18L);
        BoardEntity board = opBoard.get();
        log.info("imagePath : , {}" , board.getAttach().get(0).getFilePath());

    }
}
