package com.bb.app.repository;

import com.bb.app.entity.SendMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendMsgRepository extends JpaRepository<SendMessageEntity, Long> {
}
