package com.bb.app.DTO;

import com.bb.app.entity.MemberEntity;
import com.bb.app.entity.VoteAttachEntity;
import com.bb.app.entity.VoteReplyEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteBoardDto {
    private Long id;

    @Builder.Default
    private int agreeCount = 0;

    @Builder.Default
    private int disagreeCount = 0;

    private String title;
    private String content;
    private LocalDateTime registerDate;

    @Builder.Default
    private int viewCount = 0;

    private String writer;
    private Long member;
    private String imagePath;
    private String fileName;

    public void insertImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void insertFileName(String fileName) {
        this.fileName = fileName;
    }
}
