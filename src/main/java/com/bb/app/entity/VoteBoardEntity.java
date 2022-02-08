package com.bb.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteBoardEntity extends BaseTimeEntity {
    @Id
    @Column(name = "vote_board_id")
    private Long id;

    private int agreeCount = 0;
    private int disagreeCount = 0;

    private String title;
    private String content;
    private LocalDateTime registerDate;
    private int viewCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToMany(mappedBy = "voteBoard", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VoteAttachEntity> attach;

    @OneToMany(mappedBy = "voteBoard", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VoteReplyEntity> replyList;

}
