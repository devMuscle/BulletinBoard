package com.bb.app.repository;

import com.bb.app.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    public Optional<MessageEntity> findById(Long id);   //쪽지 내용 보기
    public void deleteById(Long id);//쪽지 삭제
    public List<MessageEntity> findByReceiverId(Long id); //쪽지 족지 리스트 조회
    public List<MessageEntity> findBySenderId(Long id); //쪽지 족지 리스트 조회

    @Query()
}
