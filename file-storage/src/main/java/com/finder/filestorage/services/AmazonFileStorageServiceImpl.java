package com.finder.filestorage.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.finder.filestorage.domain.File;
import com.finder.filestorage.domain.models.FileRequestModel;
import com.finder.filestorage.port.in.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AmazonFileStorageServiceImpl implements FileStorageService {

    private final AmazonS3 s3;

    @Override
    public File save(FileRequestModel fileRequest) {
        ObjectMetadata metadata = new ObjectMetadata();

        metadata.addUserMetadata("Content-Type", fileRequest.contentType);
        metadata.addUserMetadata("Content-Length", fileRequest.contentLength);
        metadata.addUserMetadata("fileType", fileRequest.fileType.toString());

        String fileName = UUID.randomUUID().toString() + fileRequest.filename;

        s3.putObject(fileRequest.path, fileName, fileRequest.is, metadata);

        return File.builder()
                .fileName(fileName)
                .fileType(fileRequest.fileType)
                .fullPath(fileRequest.path)
                .contentLength(fileRequest.contentLength)
                .contentType(fileRequest.contentType)
                .build();
    }

    @Override
    public List<File> save(List<FileRequestModel> fileRequest) {
        return fileRequest.stream()
                .map(this::save).collect(Collectors.toList());
    }

    @Override
    public String saveAndGetPath(FileRequestModel fileRequestModel) {
        File file = save(fileRequestModel);
        return String.format("%s/%s", file.getFullPath(), file.getFileName());
    }

    @Override
    public void createFolder(String path) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        PutObjectRequest putObjectRequest = new PutObjectRequest("", path + "/", emptyContent, metadata);

        s3.putObject(putObjectRequest);
    }

    @Override
    public byte[] download(String path) {
/*
        try {
            S3Object object = s3.getObject(path);
            S3ObjectInputStream is = object.getObjectContent();
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to download");
        }
*/

        return new byte[0];
    }
}
