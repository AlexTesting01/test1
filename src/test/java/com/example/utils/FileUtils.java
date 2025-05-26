package com.example.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<String> getFileNamesFromFolder(String folderPath) {
        List<String> names = new ArrayList<>();

        File folder = new File(folderPath);
        File[] entries = folder.listFiles();

        if (entries != null) {
            for (File entry : entries) {
                names.add(entry.getName()); // Adds both files and folders
            }
        } else {
            System.out.println("Invalid folder path or not a directory: " + folderPath);
        }

        return names;
    }

     public static String readFileContent(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null; 
        }
    }
}
