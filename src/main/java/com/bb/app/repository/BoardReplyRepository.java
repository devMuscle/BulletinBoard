package com.bb.app.repository;

import com.bb.app.entity.BoardReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardReplyRepository extends JpaRepository<BoardReplyEntity, Long> {
    @Query(value="select * from board_reply_entity where board_id = :boardNo order by if(parent_reply_id=0, board_reply_id, parent_reply_id)", nativeQuery = true)
    public List<BoardReplyEntity> selectAllByBoardNo(@Param("boardNo") Long boardNo);
}
