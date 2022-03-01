package com.bb.app.repository;

import com.bb.app.entity.MemberEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    public Optional<MemberEntity> findByLoginId(String id);
    public Optional<MemberEntity> findByLoginIdAndPassword(String id, String pwd);
    public void deleteByLoginId(String id);
}
