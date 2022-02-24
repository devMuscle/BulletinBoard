package com.bb.app.DTO;

import com.bb.app.entity.BoardAttachEntity;
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
public class BoardDetailDto {
    private Long id;

    private String title;
    private String content;
    private LocalDateTime registerDate;
    private int viewCount;
    private String writer;
    private Long memberId;
    @Builder.Default
    private List<String> imagePathList = new ArrayList<>();

    public void insertImagePath(List<BoardAttachEntity> boardAttachEntityList) {
        for(BoardAttachEntity boardAttachEntity : boardAttachEntityList) {
            this.imagePathList.add(boardAttachEntity.getFilePath());
        }
    }

    public void insertDefaultImagePath(String defaultImagePath) {
        this.imagePathList.add(defaultImagePath);
    }

}
