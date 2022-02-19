package com.bb.app.DTO;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.MessageEntity;
import com.bb.app.entity.VoteBoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;

    private String loginId;
    private String password;
    private String nickName;
    private String email;
    private int point;

    private List<BoardEntity> boardList = new ArrayList<>();
    private List<VoteBoardEntity> voteBoardList = new ArrayList<>();
    private List<MessageEntity> MessageList = new ArrayList<>();


}
