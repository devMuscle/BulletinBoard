package com.bb.app.repository;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.VoteBoardEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    public List<BoardEntity> findByMember_Id(Long id);     //id = 보드 번호 entity에서 그렇게 설정함
    public void deleteById(Long id);
    public List<BoardEntity> findAll();

    @Query(value = "select * from board_entity where member_id=:id", nativeQuery = true)
    public List<BoardEntity> findAllByMemberId(@Param("id") Long id, Pageable pageable);
}
