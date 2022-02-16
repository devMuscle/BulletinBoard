package com.bb.app.repository;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.VoteBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    public List<BoardEntity> findByMember_Id(Long id);     //id = 보드 번호 entity에서 그렇게 설정함
    public Optional<BoardEntity> findById(Long id);
    public void deleteById(Long id);
    public List<BoardEntity> findAll();
}
