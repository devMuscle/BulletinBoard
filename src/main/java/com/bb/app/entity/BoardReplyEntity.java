package com.bb.app.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BoardReplyEntity {
    @Id
    @Column(name = "board_reply_id")
    private Long id;

    private Long parentReplyId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity writer;

    private LocalDateTime registerDate;
    private String comment;
}
