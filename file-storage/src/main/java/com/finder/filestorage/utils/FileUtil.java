package com.finder.filestorage.utils;

import com.finder.filestorage.domain.File;

import java.util.Arrays;
import java.util.List;

public class FileUtil {
    private static String PNG = "PNG";
    private static String GIF = "GIF";
    private static String JPEG = "JPEG";
    private static String JPG = "JPG";
    private static String PDF = "PDF";
    private static List<String> imageTypes = Arrays.asList(PNG, GIF, JPEG, JPG);


    public static File.FileType getFileType(String contentType) {
        String type = contentType.toLowerCase();

        if(imageTypes.contains(type)) {
            return File.FileType.IMAGE;
        } else if(PDF.equals(type)) {
            return File.FileType.PDF;
        }

        return null;
    }
}
