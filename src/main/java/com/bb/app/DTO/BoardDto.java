package com.bb.app.DTO;

import com.bb.app.entity.BoardAttachEntity;
import com.bb.app.entity.BoardReplyEntity;
import com.bb.app.entity.MemberEntity;
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
public class BoardDto {
    private Long id;

    private String title;
    private String content;
    private LocalDateTime registerDate;
    private int viewCount = 0;
    private String writer;
    private MemberEntity member;

    private List<BoardAttachEntity> attach;
    private List<BoardReplyEntity> replyList;
}
