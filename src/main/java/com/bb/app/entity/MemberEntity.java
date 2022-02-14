package com.bb.app.entity;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity extends BaseTimeEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String loginId;
    private String password;
    private String nickName;
    private String email;
    private int point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<BoardEntity> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<VoteBoardEntity> voteBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<MessageEntity> MessageList = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }
}
