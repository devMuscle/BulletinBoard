package com.bb.app.repository;

import com.bb.app.entity.ReceiveMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiveMsgRepository extends JpaRepository<ReceiveMessageEntity, Long> {
}
