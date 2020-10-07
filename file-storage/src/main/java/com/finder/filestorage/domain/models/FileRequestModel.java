package com.finder.filestorage.domain.models;

import com.finder.filestorage.domain.File;
import lombok.Builder;

import java.io.InputStream;

@Builder
public class FileRequestModel {
    public String filename;
    public String path;
    public File.FileType fileType;
    public String contentLength;
    public String contentType;
    public InputStream is;
}
