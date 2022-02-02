package com.bb.app.repository;

import com.bb.app.entity.BoardReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardReplyRepository extends JpaRepository<BoardReplyEntity, Long> {
}
