package com.bb.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteAttachEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "vote_attach_id")
    private Long id;

    private String fileName;
    private String fileOriginalName;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_board_id")
    private VoteBoardEntity voteBoard;

    public void insertBoardId(VoteBoardEntity voteboard) {
        this.voteBoard = voteboard;
    }
}
