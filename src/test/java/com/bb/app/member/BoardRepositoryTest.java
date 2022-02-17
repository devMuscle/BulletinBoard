package com.bb.app.member;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.MemberEntity;
import com.bb.app.repository.BoardRepository;
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
class BoardRepositoryTest {


    @Autowired
    BoardRepository repository;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    @Transactional
    @Rollback(value = false)
    void test() {
//        List<BoardEntity> m = repository.findByMember_Id(6L);
//        for(BoardEntity board : m) {
//
//            logger.info(board.getTitle()+board.getContent()+board.getId()+board.getMember());
//        }
//        Optional<BoardEntity> board = repository.findById(17L);
//        logger.info(board.get().getTitle());

//         List<BoardEntity> board = repository.findAll();
//         for(BoardEntity b : board){
//             logger.info("제목"+b.getTitle());
//             logger.info("내용"+b.getContent());
//             logger.info("작성자"+b.getWriter());
//             logger.info("글번호"+b.getId().toString());
//         }
        Optional<BoardEntity> board = repository.findById(1L);
        board.get().UpdateContent("내용2");
    }
}
