package com.bb.app.member;

import static org.junit.jupiter.api.Assertions.*;

import com.bb.app.DTO.MemberDto;
import com.bb.app.Mapper.MemberMapper;
import com.bb.app.DTO.MessageDto;
import com.bb.app.Mapper.MessageMapper;
import com.bb.app.entity.MessageEntity;
import org.apache.catalina.authenticator.SavedRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bb.app.repository.MemberRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    @Transactional
    @Rollback(value = false)
    void test() {
        Optional<MemberEntity> opMember = repository.findById(1L);
        MemberEntity member = opMember.get();

        MemberDto dtoMember = MemberMapper.INSTANCE.toDto(member);

        assertEquals(dtoMember.getId(), member.getId());
        logger.info(dtoMember.getNickName());
        logger.info(dtoMember.getEmail());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void DeleteTest(){

        MessageDto msg = MessageDto.builder()
                .title("제목text")
                .content("내용text")
                .senderId(1L)
                .receiverId(2L)
                .build();
        MessageEntity msgEntity = MessageMapper.INSTANCE.toEntity(msg);

        Optional<MemberEntity> mem = repository.findById(msg.getSenderId());
        MemberEntity member = mem.get();
        logger.info("맵버: " + member.getId());
        member.UpdateMessageList(msgEntity);


    }
}
