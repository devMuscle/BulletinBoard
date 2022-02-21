package com.bb.app.member;

import static org.junit.jupiter.api.Assertions.*;

import com.bb.app.DTO.MemberDto;
import com.bb.app.Mapper.MemberMapper;
import org.apache.catalina.authenticator.SavedRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bb.app.entity.MemberEntity;
import com.bb.app.repository.MemberRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
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
        repository.deleteByLoginId("testId");
    }
}
