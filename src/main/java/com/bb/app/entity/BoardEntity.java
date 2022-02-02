package com.bb.app.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BoardEntity {
    @Id
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private LocalDateTime registerDate;
    private int viewCount = 0;
    private String writer;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<BoardAttachEntity> attach;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<BoardReplyEntity> replyList;
}
