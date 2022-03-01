package com.bb.app.repository;

import com.bb.app.entity.MessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface  MessageRepository extends JpaRepository<MessageEntity, Long> {
    public Optional<MessageEntity> findById(Long id);   //쪽지 내용 보기
    public void deleteById(Long id);//쪽지 삭제

    @Query(value = "select * from message_entity where receiver_id=:id", nativeQuery = true)
    public List<MessageEntity> findByReceiverId(@Param("id") Long id, Pageable pageable); //받은 쪽지 리스트 조회

    @Query(value = "select * from message_entity where sender_id=:id", nativeQuery = true)
    public List<MessageEntity> findBySenderId(@Param("id") Long id, Pageable pageable); //보낸 쪽지 리스트 조회

    @Query(value = "select * from message_entity where receiver_id=:id or sender_id=:id order by created_date", nativeQuery = true)
    public List<MessageEntity> findAllBySenderIdOrReceiverId(@Param("id") Long id, Pageable pageable);
}
