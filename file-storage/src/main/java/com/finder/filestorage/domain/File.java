package com.finder.filestorage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private Long id;
    private String fileName;
    private FileType fileType;
    private String fullPath;
    private String contentLength;
    private String contentType;

    public enum FileType {
        IMAGE, PDF
    }
}
