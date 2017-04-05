package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File inFile = new File(args[0]);
        File zip = new File(args[1]);
        Path tmp = Files.createTempFile(null, null);
        try (ZipInputStream inputStream = new ZipInputStream(new FileInputStream(args[1]))) {
            try (ZipOutputStream outputStream = new ZipOutputStream(Files.newOutputStream(tmp))) {
                ZipEntry zipEntry;
                List<Path> pathList = new LinkedList<>();
                Path addFile = Paths.get("new").resolve(inFile.getName());
                while ((zipEntry = inputStream.getNextEntry()) != null) {
                    String name = zipEntry.getName();
                    pathList.add(Paths.get(name));
                    if (!addFile.toString().equals(name)) {
                        outputStream.putNextEntry(new ZipEntry(name));
                        copy(inputStream, outputStream);
                        inputStream.closeEntry();
                    }
                    outputStream.closeEntry();
                }
                outputStream.putNextEntry(new ZipEntry(addFile.toString()));
                try (FileInputStream fileInputStream = new FileInputStream(inFile.toString())) {
                    copy(fileInputStream, outputStream);
                }
                outputStream.closeEntry();
            }
        }
        Files.copy(tmp, zip.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bytes = new byte[1024*8];
        int len;
        while ((len = inputStream.read(bytes)) > 0)
            outputStream.write(bytes, 0 ,len);
    }
}
