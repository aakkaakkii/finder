package com.finder.filestorage.adapter.web;

import com.finder.filestorage.domain.File;
import com.finder.filestorage.domain.models.FileRequestModel;
import com.finder.filestorage.port.in.FileStorageService;
import com.finder.filestorage.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileStorageRestController {

    private final FileStorageService fileStorageService;

    @PostMapping
    public String saveFile(@RequestParam String path, @RequestParam MultipartFile file) throws IOException {

        File savedFile = fileStorageService.save(FileRequestModel.builder()
                .fileType(FileUtil.getFileType(
                        Objects.requireNonNull(file.getContentType())
                ))
                .filename(file.getOriginalFilename())
                .is(file.getInputStream())
                .contentLength(String.valueOf(file.getSize()))
                .contentType(file.getContentType())
                .path(path)
                .build());

        return savedFile.getFullPath();
    }

    @PostMapping("/multiple")
    public List<String> saveFiles(@RequestParam String path, @RequestParam("files") MultipartFile[] files)
            throws IOException {
        List<FileRequestModel> filesRequest = new ArrayList<>();

        for (MultipartFile file : files) {
            filesRequest.add(FileRequestModel.builder()
                    .fileType(FileUtil.getFileType(
                            Objects.requireNonNull(file.getContentType())
                    ))
                    .filename(file.getOriginalFilename())
                    .is(file.getInputStream())
                    .contentLength(String.valueOf(file.getSize()))
                    .contentType(file.getContentType())
                    .path(path)
                    .build());
        }

        List<File> savedFiles = fileStorageService.save(filesRequest);

        return savedFiles.stream().map(File::getFullPath).collect(Collectors.toList());
    }

    @PostMapping("/folder")
    public void createFolder(@RequestParam String path) throws IOException {
        fileStorageService.createFolder(path);
    }

    @DeleteMapping("/multiple")
    public void deleteFiles(@RequestParam String[] paths) {
        //TODO: add folder delete
        fileStorageService.delete(Arrays.asList(paths));

    }

    @DeleteMapping
    public void deleteFile(@RequestParam String path) {
        fileStorageService.delete(path);
    }
}
