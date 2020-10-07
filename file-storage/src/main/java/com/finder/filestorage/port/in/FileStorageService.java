package com.finder.filestorage.port.in;

import com.finder.filestorage.domain.File;
import com.finder.filestorage.domain.models.FileRequestModel;

import java.util.List;

public interface FileStorageService {
    File save(FileRequestModel fileRequest);
    List<File> save(List<FileRequestModel> fileRequest);
    String saveAndGetPath(FileRequestModel fileRequestModel);
    void createFolder(String path);
    byte[] download(String path);
}
