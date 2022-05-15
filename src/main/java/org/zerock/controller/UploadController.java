package org.zerock.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@Log4j2
public class UploadController {

    @PostMapping("/upload1")
    public void upload1(MultipartFile[] files) {

        log.info("----------------------------------");

        log.info(files);

        for (MultipartFile file : files) {

            String originalFileName = file.getOriginalFilename();

            String saveName = UUID.randomUUID().toString() + "_" + originalFileName;
            //파일이름 중복방지를 위한 uuid추가

            log.info(file.getResource());
            String saveFolder = makeFolders();


            try (InputStream in = file.getInputStream();
                 FileOutputStream fos = new FileOutputStream("C:\\upload\\" +saveFolder+"\\"+ saveName);
            ) {
                FileCopyUtils.copy(in, fos);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }//end for


    }

    private String makeFolders() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //이 양식으로 폴더를 만들것
        String str = sdf.format(new Date());

        File folderPath = new File("C:\\upload\\" + str);

        if (!folderPath.exists()) {
            //폴더가 있는지 확인
            folderPath.mkdirs();
        }
        return str;

    }
}
