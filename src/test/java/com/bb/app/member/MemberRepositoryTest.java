package com.bb.app.member;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.catalina.authenticator.SavedRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bb.app.entity.MemberEntity;
import com.bb.app.repository.MemberRepository;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;

    @Test
    void test() {
        MemberEntity m = MemberEntity.builder()
                .id(3L)
                .email("agag")
                .loginId("hi")
                .build();
    }

}
