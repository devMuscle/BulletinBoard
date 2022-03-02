package com.bb.app.repository;

import com.bb.app.entity.VoteBoardEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteBoardRepository extends JpaRepository<VoteBoardEntity, Long> {

    public List<VoteBoardEntity> findByMember_Id(Long id);
    public void deleteById(Long id);
    public List<VoteBoardEntity> findAll();

    @Query(value = "select * from vote_board_entity where member_id=:id", nativeQuery = true)
    public List<VoteBoardEntity> findAllByMemberId(@Param("id") Long id, Pageable pageable);
}
