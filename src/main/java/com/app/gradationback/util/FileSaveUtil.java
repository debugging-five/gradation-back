package com.app.gradationback.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileSaveUtil {
    public void fileSave(MultipartFile file, String filePath, String fileName) throws IOException{

        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File dest = new File("C:/upload/" + filePath + "/" + fileName);
        file.transferTo(dest);
    }
}
