package com.finder.filestorage.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.finder.filestorage.domain.File;
import com.finder.filestorage.domain.models.FileRequestModel;
import com.finder.filestorage.port.in.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmazonFileStorageServiceImpl implements FileStorageService {

    @Value("${amazon.bucket.name}")
    public String BUCKET_NAME;
    @Value("${amazon.bucket.url}")
    public String BUCKET_URL;
    private final AmazonS3 s3;

    @Override
    public File save(FileRequestModel fileRequest) {
        ObjectMetadata metadata = new ObjectMetadata();

        metadata.addUserMetadata("Content-Type", fileRequest.contentType);
        metadata.addUserMetadata("Content-Length", fileRequest.contentLength);
        metadata.addUserMetadata("fileType", fileRequest.fileType.toString());
        metadata.setContentType(fileRequest.contentType);


        String fileName = String.format("%s_%s",
                UUID.randomUUID().toString(),
                fileRequest.filename.replace("/", "_"));
        String path = String.format("%s%s", BUCKET_NAME, fileRequest.path);

        s3.putObject(path, fileName, fileRequest.is, metadata);

        return File.builder()
                .fileName(fileName)
                .fileType(fileRequest.fileType)
                .fullPath(String.format("%s%s/%s",BUCKET_URL, fileRequest.path, fileName))
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
    public void delete(List<String> paths) {
        String[] asd = paths.stream()
                .map(p -> p.replaceFirst(BUCKET_URL, "").replaceFirst("/", ""))
                .toArray(String[]::new);

        DeleteObjectsRequest d = new DeleteObjectsRequest(BUCKET_NAME)
                .withKeys(asd)
                .withQuiet(false);
        s3.deleteObjects(d);
    }

    @Override
    public void delete(String path) {
        delete(Collections.singletonList(path));
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
        path = path.startsWith("/") ? path.substring(1) : path;
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, path + "/", emptyContent, metadata);

        s3.putObject(putObjectRequest);
    }

    @Override
    public byte[] download(String path) {
        try {
            S3Object object = s3.getObject(BUCKET_NAME, path);
            S3ObjectInputStream is = object.getObjectContent();
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to download");
        }
    }
}
