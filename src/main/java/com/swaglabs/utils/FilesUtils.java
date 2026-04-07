package com.swaglabs.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilesUtils {
    private FilesUtils() {
        super();
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            LogsUtil.warn("No files found in directory: " + folderPath);
            return null;
        }
        File latestFile = files[0];
        for (File file : files) {
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }
        return latestFile;
    }

    public static void deleteFiles(File dirPath) {
        if (dirPath == null || !dirPath.exists()) {
            LogsUtil.warn("Directory doesn't exist: " + dirPath);
        }
        File[] fileList = dirPath.listFiles();
        if (fileList == null) {
            LogsUtil.warn("Failed to list files in: " + dirPath);
        }
        for (File file : fileList) {
            if (file.isDirectory()) {
                deleteFiles(file);
            } else {
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    LogsUtil.error("Failed to delete file : " + file);
                }


            }
        }
    }

    public static void cleanDirectory(File file) {
        try {
            FileUtils.deleteQuietly(file);
        } catch (Exception e) {
            LogsUtil.error(e.getMessage());
        }
    }

}
