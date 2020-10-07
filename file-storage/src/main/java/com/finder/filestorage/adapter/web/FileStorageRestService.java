package com.finder.filestorage.adapter.web;

import com.finder.filestorage.domain.File;
import com.finder.filestorage.domain.models.FileRequestModel;
import com.finder.filestorage.port.in.FileStorageService;
import com.finder.filestorage.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileStorageRestService {

    private final FileStorageService fileStorageService;

    @PostMapping
    public void saveFile(@RequestParam String path, @RequestParam MultipartFile file) throws IOException {

        fileStorageService.save(FileRequestModel.builder()
                .fileType(FileUtil.getFileType(
                        Objects.requireNonNull(file.getContentType())
                ))
                .filename(file.getName())
                .is(file.getInputStream())
                .contentLength(String.valueOf(file.getSize()))
                .contentType(file.getContentType())
                .path(path)
                .build());
    }

    @PostMapping("/multipleSave")
    public void saveFiles(@RequestParam String path, @RequestParam MultipartFile[] files) throws IOException {
        List<FileRequestModel> filesRequest = new ArrayList<>();

        for (MultipartFile file : files) {
            filesRequest.add(FileRequestModel.builder()
                    .fileType(FileUtil.getFileType(
                            Objects.requireNonNull(file.getContentType())
                    ))
                    .filename(file.getName())
                    .is(file.getInputStream())
                    .contentLength(String.valueOf(file.getSize()))
                    .contentType(file.getContentType())
                    .path(path)
                    .build());
        }

        fileStorageService.save(filesRequest);
    }
}
