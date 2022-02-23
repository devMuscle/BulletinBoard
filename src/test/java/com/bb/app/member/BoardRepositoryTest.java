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

    }
}
