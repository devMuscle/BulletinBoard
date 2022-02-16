package com.bb.app.entity;

import com.bb.app.constant.AgreeStatus;
import com.bb.app.constant.DeleteStatus;
import com.bb.app.constant.ReadStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteReplyEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "vote_reply_id")
    private Long id;

    private Long parentReplyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_board_id")
    private VoteBoardEntity voteBoard;

    @ManyToOne(fetch = FetchType.LAZY)
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
