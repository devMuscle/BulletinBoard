package com.bb.app.entity;

import javax.persistence.*;

@Entity
public class VoteAttachEntity {
    @Id
    @Column(name = "vote_attach_id")
    private Long id;

    private String fileName;
    private String fileOriginalName;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "vote_board_id")
    private VoteBoardEntity voteBoard;
}
