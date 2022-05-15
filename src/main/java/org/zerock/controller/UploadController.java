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

@Controller
@Log4j2
public class UploadController {

    @PostMapping("/upload1")
    public void upload1(MultipartFile[] files){

       log.info("----------------------------------");

       log.info(files);

        for (MultipartFile file:files) {
            log.info(file.getOriginalFilename());
            log.info(file.getResource());
            log.info("-----------------------");



            try(InputStream in = file.getInputStream();
                FileOutputStream fos = new FileOutputStream("C:\\Upload\\"+file.getOriginalFilename());
            )
            {
                FileCopyUtils.copy(in,fos);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }//end for

    }
}
