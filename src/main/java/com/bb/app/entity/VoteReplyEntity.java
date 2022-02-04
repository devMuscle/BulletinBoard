package com.bb.app.entity;

import com.bb.app.constant.AgreeStatus;
import com.bb.app.constant.DeleteStatus;
import com.bb.app.constant.ReadStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VoteReplyEntity extends BaseTimeEntity {
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
    
    @Enumerated(EnumType.STRING)
    private ReadStatus readStatus;
    
    @Enumerated(EnumType.STRING)
    private DeleteStatus reciveDeleteStatus;
    
    @Enumerated(EnumType.STRING)
    private DeleteStatus sendDeleteStatus;
}
