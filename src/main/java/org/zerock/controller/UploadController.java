package org.zerock.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

public class UploadController {

    @PostMapping("/upload1")
    public void upload1(MultipartFile[] files){

    }
}
