package io.youngwon.app.domain.files.dto;

import io.youngwon.app.domain.files.Files;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FilesListResponseDto {

    private Long id;
    private String path;
    private String filename;

    public FilesListResponseDto(Files files){
        this.id = files.getId();
        this.path = files.getPath();
        this.filename = files.getFilename();

    }

}
