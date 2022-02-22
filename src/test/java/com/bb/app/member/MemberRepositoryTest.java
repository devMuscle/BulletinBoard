package com.bb.app.member;

import com.bb.app.Mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
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
    @Autowired
    MemberMapper memberMapper;

    @Test
    @Transactional
    @Rollback(value = false)
    void DeleteTest(){
        repository.deleteByLoginId("testId");
    }
}
