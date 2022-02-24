package com.bb.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteBoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "vote_board_id")
    private Long id;

    private int agreeCount = 0;
    private int disagreeCount = 0;

    private String title;
    private String content;
    private LocalDateTime registerDate;
    private int viewCount = 0;
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToMany(mappedBy = "voteBoard", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<VoteAttachEntity> attach = new ArrayList<>();;

    @OneToMany(mappedBy = "voteBoard", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<VoteReplyEntity> replyList = new ArrayList<>();;

    public void UpdateTile(String newTile){
        this.title = newTile;
    }

    public void UpdateContent(String newContent){
        this.content = newContent;
    }

    public void UpdateVoteReplyList(final VoteReplyEntity msg){
        replyList.add(msg);
    }

    public void addBoardAttachList(List<VoteAttachEntity> voteAttachEntityList) {
        for(VoteAttachEntity voteAttachEntity : voteAttachEntityList) {
            voteAttachEntity.insertBoardId(this);
            attach.add(voteAttachEntity);
        }
    }
}
