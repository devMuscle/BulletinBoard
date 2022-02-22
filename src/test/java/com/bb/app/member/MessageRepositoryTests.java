package com.bb.app.member;

import com.bb.app.DTO.MessageDto;
import com.bb.app.Mapper.MessageMapper;
import com.bb.app.entity.MemberEntity;
import com.bb.app.entity.MessageEntity;
import com.bb.app.repository.MemberRepository;
import com.bb.app.repository.MessageRepository;
import org.aspectj.bridge.Message;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class MessageRepositoryTests {


    @Autowired
    MessageRepository msgRepository;
    MemberRepository memRepository;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    @Transactional
    @Rollback(value = false)
    public void dtoToEntity() {

        MessageDto msgDto = MessageDto.builder()
                .title("제목2")
                .content("내용2")
                .senderId(1L)
                .receiverId(2L)
                .build();

        MessageEntity msg =  MessageMapper.INSTANCE.toEntity(msgDto);

        msgRepository.save(msg);



    }

//    void test() {
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
