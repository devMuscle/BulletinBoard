package com.bb.app.member;

import com.bb.app.DTO.MessageDto;
import com.bb.app.Mapper.MessageMapper;
import com.bb.app.entity.MessageEntity;
import com.bb.app.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class MessageRepositoryTests {


    @Autowired
    MessageRepository repository;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    @Transactional
    @Rollback(value = false)
    public void dtoToEntity() {
        MessageDto personDto = MessageDto.builder()
                .id(4L)
                .content("test1")
                .title("titile")
                .build();

        MessageEntity person = MessageMapper.INSTANCE.toEntity(personDto);  //service 등록 후 사용

        assertEquals(personDto.getContent(), person.getContent());
        assertEquals(personDto.getTitle(), person.getTitle());
    }

    void test() {
//        List<BoardEntity> m = repository.findByMember_Id(6L);
//        for(BoardEntity board : m) {
//
//            logger.info(board.getTitle()+board.getContent()+board.getId()+board.getMember());
//        }
//        Optional<BoardEntity> board = repository.findById(17L);
//        logger.info(board.get().getTitle());

//        List<MessageEntity> msg = repository.findBySenderId(2L);
//        for(MessageEntity b : msg){
//            logger.info("제목"+b.getTitle());
//            logger.info("내용"+b.getContent());
//            logger.info("글번호"+b.getId().toString());
//        }
    }
}
