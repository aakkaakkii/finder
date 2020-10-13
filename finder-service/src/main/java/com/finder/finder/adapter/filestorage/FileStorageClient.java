package com.finder.finder.adapter.filestorage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient("file-storage")
public interface FileStorageClient {
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/v1/file"
    )
    String saveFile(@RequestParam("path") String path, @RequestParam("file") MultipartFile file);

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/v1/file/multiple"
    )
    List<String> saveFiles(@RequestParam("path") String path, @RequestParam("files") MultipartFile[] files);

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/v1/file/folder"
    )
    List<String> createFolder(@RequestParam("path") String path);

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/api/v1/file"
    )
    void deleteFile(@RequestParam("path") String path);

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/api/v1/file/multiple"
    )
    void deleteFiles(@RequestParam("paths") String[] paths);

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/api/v1/file/folder"
    )
    void deleteFolder(@RequestParam("path") String path);
}
