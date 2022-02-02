package com.bb.app.entity;

import com.bb.app.constant.AgreeStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VoteReplyEntity {
    @Id
    @Column(name = "vote_reply_id")
    private Long id;

    private Long parentReplyId;

    @ManyToOne
    @JoinColumn(name = "vote_board_id")
    private VoteBoardEntity voteBoard;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity writer;

    private LocalDateTime registerDate;
    private String comment;

    @Enumerated(EnumType.STRING)
    private AgreeStatus agreeStatus;
}
