package com.bb.app.repository;

import com.bb.app.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    public Optional<MemberEntity> findByloginId(String id);
    public Optional<MemberEntity> findByLoginIdAndPassword(String id, String pwd);
    public void deleteByLoginId(String id);
}
