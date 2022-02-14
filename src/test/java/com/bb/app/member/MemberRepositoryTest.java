package com.bb.app.member;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.catalina.authenticator.SavedRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bb.app.entity.MemberEntity;
import com.bb.app.repository.MemberRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;

    @Test
    @Transactional
    @Rollback(value = false)
    void test() {
        MemberEntity m = MemberEntity.builder()
                .id(4L)
                .loginId("test")
                .password("testpass")
                .nickName("hongs")
                .email("agag")
                .point(0)
                .build();

        repository.save(m);


    }
}
