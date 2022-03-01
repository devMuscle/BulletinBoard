package com.bb.app.DTO;

import com.bb.app.entity.BoardAttachEntity;
import com.bb.app.entity.VoteAttachEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteBoardDetailDto {
    private Long id;

    private int agreeCount;
    private int disagreeCount;

    private String title;
    private String content;
    private LocalDateTime registerDate;
    private int viewCount;
    private String writer;
    private Long memberId;
    @Builder.Default
    private List<String> imagePathList = new ArrayList<>();
    @Builder.Default
    private List<String> fileNameList = new ArrayList<>();
    @Builder.Default
    private List<VoteReplyDto> voteReplyDtoList = new ArrayList<>();

    public void insertImagePath(List<VoteAttachEntity> voteAttachEntityList) {
        for(VoteAttachEntity voteAttachEntity : voteAttachEntityList) {
            this.imagePathList.add(voteAttachEntity.getFilePath());
        }
    }

    public void insertFileName(List<VoteAttachEntity> voteAttachEntityList) {
        for(VoteAttachEntity voteAttachEntity : voteAttachEntityList) {
            this.fileNameList.add(voteAttachEntity.getFileName());
        }
    }

    public void insertDefaultImagePath(String defaultImagePath) {
        this.imagePathList.add(defaultImagePath);
    }

    public void insertDefaultFileName(String defaultFileName) {
        this.fileNameList.add(defaultFileName);
    }

    public void insertResBoardReply(List<VoteReplyDto> replyDtos) {
        for(VoteReplyDto replyDto : replyDtos) {
            this.voteReplyDtoList.add(replyDto);
        }
    }
}
