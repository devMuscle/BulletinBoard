package com.bb.app.DTO;

import com.bb.app.entity.BoardAttachEntity;
import com.bb.app.entity.BoardReplyEntity;
import com.bb.app.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Long id;

    private String title;
    private String content;
    private LocalDateTime registerDate;
    private int viewCount;
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
