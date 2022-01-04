package com.MultipleTableFetch.Helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public final String Upload_DIR = "//home//prince//Downloads//MultipleTableFetch//src//main//resources//static//images";
    public final String Upload_DIR1 = "//home//prince//Downloads//MultipleTableFetch//src//main//resources//static//banner";

    public String uploadFile(MultipartFile file) throws IOException {
        boolean f = false;
        try {
            /*InputStream inputStream = file.getInputStream();
            byte data[] = new byte[inputStream.available()];
            inputStream.read(data);
            FileOutputStream fileOutputStream = new FileOutputStream(Upload_DIR + File.separator + file.getOriginalFilename());
            fileOutputStream.write(data);
            fileOutputStream.flush();
            fileOutputStream.close();*/
            Files.copy(file.getInputStream(), Paths.get(Upload_DIR + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f = true;
            if (f)
                return Upload_DIR + File.separator + file.getOriginalFilename();
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //   return f;
        return null;
    }

    public String[] uploadFile1(MultipartFile file, MultipartFile file1) throws IOException {
        boolean f = false;
        try {
            Files.copy(file.getInputStream(), Paths.get(Upload_DIR1 + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(file1.getInputStream(), Paths.get(Upload_DIR1 + File.separator + file1.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            String[] str = new String[2];
            str[0] = Upload_DIR1 + File.separator + file.getOriginalFilename();
            str[1] = Upload_DIR1 + File.separator + file1.getOriginalFilename();
            f = true;
            if (f)
                return str;
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
