package com.bb.app.repository;

import com.bb.app.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiveMsgRepository extends JpaRepository<MessageEntity, Long> {
}
