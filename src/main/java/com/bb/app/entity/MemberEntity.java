package com.bb.app.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity extends BaseTimeEntity {
    @Id
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String password;
    private String nickName;
    private String email;
    private int point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> boardList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VoteBoardEntity> voteBoardList;
    
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MessageEntity> receiveMessageList;
}
