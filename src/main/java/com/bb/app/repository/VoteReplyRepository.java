package com.bb.app.repository;

import com.bb.app.entity.VoteReplyEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteReplyRepository extends JpaRepository<VoteReplyEntity, Long> {

    @Query(value="select * from vote_reply_entity where vote_board_id = :boardNo order by if(parent_reply_id=0, vote_reply_id, parent_reply_id)", nativeQuery = true)
    public List<VoteReplyEntity> selectAllByBoardNo(@Param("boardNo") Long boardNo);

    @Query(value = "select * from vote_reply_entity where member_id=:id", nativeQuery = true)
    public List<VoteReplyEntity> findAllByMemberId(@Param("id") Long id, Pageable pageable);

}
