package com.bb.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private LocalDateTime registerDate;
    private int viewCount = 0;
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardAttachEntity> attach;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardReplyEntity> replyList;

    public void UpdateTile(String newTile){
        this.title = newTile;
    }
    public void UpdateContent(String newContent){
        this.content = newContent;
    }

}
