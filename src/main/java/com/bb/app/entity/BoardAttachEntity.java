package com.bb.app.entity;

import javax.persistence.*;

@Entity
public class BoardAttachEntity extends BaseTimeEntity {
    @Id
    @Column(name = "board_attach_id")
    private Long id;

    private String fileName;
    private String fileOriginalName;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;
}
