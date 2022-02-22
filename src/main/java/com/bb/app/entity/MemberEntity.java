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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    public void UpdatePassword(String newPassword){
        this.password = newPassword;
    }
    public void UpdateEmail(String newEmail){
        this.email = newEmail;
    }
    public void UpdateNickname(String newNickname){
        this.nickName = newNickname;
    }

    public void UpdateboardList(final BoardEntity board){
        boardList.add(board);
    }
    public void UpdatevoteBoardList(final VoteBoardEntity vBoard){
        voteBoardList.add(vBoard);
    }
    public void UpdateMessageList(final MessageEntity msg){
        MessageList.add(msg);
    }


}
