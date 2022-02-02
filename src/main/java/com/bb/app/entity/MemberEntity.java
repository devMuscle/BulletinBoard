package com.bb.app.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class MemberEntity {
    @Id
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String password;
    private String nickName;
    private String email;
    private int point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<BoardEntity> boardList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<VoteBoardEntity> voteBoardList;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<SendMessageEntity> sendMessageList;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<ReceiveMessageEntity> receiveMessageList;
}
