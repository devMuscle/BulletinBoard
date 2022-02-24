package com.bb.app.DTO;

import com.bb.app.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardAttachDto {
    private Long id;
    private String fileName;
    private String fileOriginalName;
    private String filePath;

}
