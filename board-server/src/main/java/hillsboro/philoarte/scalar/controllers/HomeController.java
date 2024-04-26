package hillsboro.philoarte.scalar.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * packageName: kr.co.mibot.api.image.controllers
 * fileName   : ImageController
 * author     : Jangwonjong
 * date       : 2022-06-18
 * desc       :
 * ======================================
 * DATE          AUTHOR            NOTE
 * ======================================
 * 2022-06-18     Jangwonjong       최초 생성
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RequiredArgsConstructor

public class HomeController {
    @GetMapping("/")
    public String now(){
        System.out.println(" 작동함 ");
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadImages(@RequestPart(value="file",required = false) List<MultipartFile> files) {
        System.out.println(" 들어옴 ");
        for (MultipartFile file : files) {
            if (!file.getContentType().startsWith("image")) {
                System.out.println("이미지 파일이 아닙니다.");

                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }else{
                System.out.println("이미지 파일이 맞습니다.");
            }
        }

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
