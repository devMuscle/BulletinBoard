package com.bb.app.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardAttachEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "board_attach_id")
    private Long id;

    private String fileName;
    private String fileOriginalName;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    public void insertBoardId(BoardEntity board) {
        this.board = board;
    }
}
