package org.zerock.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.dto.UploadResultDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
public class UploadController {
    @GetMapping("/view")
    @ResponseBody
    //filename을 받아오게 사용하고 싶지만 파일의 링크가 / 때문에 path로 끊겨 힘들다
    //그럼 쿼리스트링으로 넘기자
    public ResponseEntity<byte[]> viewFile(String fileName){
        //파일 전송은 byte[]이지
        log.info("==============================");
        log.info("file name.............."+ fileName);

        File targetFile = new File("C:\\upload\\"+fileName);
        log.info(targetFile);
        //파일 존재 확인

        //이제 파일을 jsp로 보내야지
        //근데 브라우저에 값을 보낼때는 mime이런거 보냈잖아? 서블릿 생각해봐
        try {
            String mimeType = Files.probeContentType(targetFile.toPath());
            // 마임타입 확인 ㅇㅋ 이제 응답해줘야지
            //브라우저에 이미지를 보내야하는데 http에 맞춰 보내야한다
             log.info(mimeType);

             //이제 entty로 바꿧고 byte[]보내야 하니까 수정하자
            byte[] data = FileCopyUtils.copyToByteArray(targetFile);
            //유틸이 있어서 편하네
            return ResponseEntity.ok().header("Content-Type",mimeType).body(data);
            //성공한 경우
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
            //실패할 경우 404에러를 보낸다
        }


    }



    @PostMapping("/upload1")
    @ResponseBody //json 통신을 위해
    public List<UploadResultDTO> upload1(MultipartFile[] files){

        log.info("=========================");

        log.info(files);

        List<UploadResultDTO> list = new ArrayList<>();
        //이 형식으로 값을 만들어서 보내기 위해 빈 배열생성

        //업로드된 파일이 있다고 가정
        for (MultipartFile file:files) {

            String originalFileName = file.getOriginalFilename();

            boolean img = file.getContentType().startsWith("image");
            //마임타입으로 image 파일인지 확인

            String uuid = UUID.randomUUID().toString();
            //dto를통해 전송할꺼다

            String saveName = uuid +"_"+ originalFileName;

            log.info(file.getResource());

            String saveFolder = makeFolders();

            File saveFile = new File("C:\\upload\\" +saveFolder+"\\"+saveName);

            try (InputStream in = file.getInputStream();
                 FileOutputStream fos = new FileOutputStream(saveFile);
                 //try with resource
            ){
                FileCopyUtils.copy(in, fos);
                //input 을 output으로 꺼내는 반복을 해줌
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(img){
                //saveName = UUID+"_"+fileName
                String thumbFileName = saveFolder+"\\s_"+saveName;
                //섬네일 파일명

                File thumbFile = new File("C:\\upload\\" +thumbFileName);
                //섬네일 파일 경로
                try {
                    Thumbnails.of(saveFile)
                            .size(200,200)
                            .toFile(thumbFile);
                    //섬네일 제작 라이브러리
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            list.add(UploadResultDTO.builder()
                    .original(originalFileName)
                    .uuid(uuid)
                    .img(img)
                    .savePath(saveFolder)
                    .build());

            //통신을 DTO로 하기 위해 builder를 이용해 전달
            log.info("--------------------------");

        }//end for

        return list;
        //이걸 return은 jsp에 다시 보내는건가? ㅇㅇ
        //http규약에 맞게 이미지를 보내야 해서 entity 를 사용함
        //byte의 배열로 보내야함
    }


    private String makeFolders(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        String str = sdf.format(new java.util.Date());

        File folderPath = new File("C:\\upload\\" + str);

        if(!folderPath.exists()){
            folderPath.mkdirs();
        }

        return str;

    }

}
