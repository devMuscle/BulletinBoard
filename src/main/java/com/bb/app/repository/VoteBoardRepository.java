package com.bb.app.repository;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.VoteBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteBoardRepository extends JpaRepository<VoteBoardEntity, Long> {

    public List<VoteBoardEntity> findByMember_Id(Long id);
    //public Optional<VoteBoardEntity> findById(Long id);
    public void deleteById(Long id);
    public List<VoteBoardEntity> findAll();
}
