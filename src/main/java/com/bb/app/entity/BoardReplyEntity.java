package com.bb.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardReplyEntity extends BaseTimeEntity {
    @Id
    @Column(name = "board_reply_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private Long parentReplyId = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private BoardEntity board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private MemberEntity writer;

    private LocalDateTime registerDate;
    private String comment;

    public void UpdateComment(String newComment) {
        this.comment = newComment;
    }
}
