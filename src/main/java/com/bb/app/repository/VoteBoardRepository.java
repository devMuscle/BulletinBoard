package com.bb.app.repository;

import com.bb.app.entity.VoteBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteBoardRepository extends JpaRepository<VoteBoardEntity, Long> {
}
