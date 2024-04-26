package hillsboro.philoarte.scalar.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.philoarte.api.review.domain.dto.PageRequestDto;
import shop.philoarte.api.review.domain.dto.PageResultDto;
import shop.philoarte.api.review.domain.dto.ReviewDto;
import shop.philoarte.api.review.domain.dto.ReviewFileDto;
import shop.philoarte.api.review.service.ReviewFileServiceImpl;
import shop.philoarte.api.review.service.ReviewServiceImpl;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping(value = "/reviews", method = { RequestMethod.GET, RequestMethod.POST })
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Api(tags = "reviews")
public class ReviewController {

    private final ReviewServiceImpl service;
    private final ReviewFileServiceImpl reviewFileService;

    @Value("${shop.upload.path}")
    private String uploadPath;

    @PostMapping("/register")
    @ApiOperation(value = "리뷰 게시글 등록", notes = "리뷰 게시글을 등록 합니다.")
    public ResponseEntity<Map<String, Long>> reviewSave(ReviewDto reviewDto) {

        ArrayList<MultipartFile> files = reviewDto.getFiles();

        files.forEach(f -> {

            String uuid = UUID.randomUUID().toString();

            String saveName = uploadPath + File.separator + uuid + "_" + f.getOriginalFilename();
            String thumbnailSaveName = uploadPath + File.separator + "s_" + uuid + f.getOriginalFilename();

            try {
                FileCopyUtils.copy(f.getInputStream(),
                        new FileOutputStream(saveName, Boolean.parseBoolean(thumbnailSaveName)));
                Thumbnails.of(new File(saveName)).size(600, 600).outputFormat("jpg").toFile(thumbnailSaveName);

                ReviewFileDto fileDto = ReviewFileDto.builder().uuid(uuid).imgName(f.getOriginalFilename())
                        .path(uploadPath).build();

                reviewDto.addReviewFileDto(fileDto);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("result", (service.save(reviewDto)));

        return new ResponseEntity(resultMap, HttpStatus.OK);
    }

    @GetMapping("/list/pages")
    @ApiOperation(value = "리뷰 게시글 목록", notes = "리뷰 게시글의 목록을 보여줍니다.")
    public ResponseEntity<PageResultDto<ReviewDto, Object[]>> reviewList(PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(service.getList(pageRequestDto));
    }

    @GetMapping("/read/{reviewId}")
    @ApiOperation(value = "하나의 리뷰 읽기", notes = "하나의 리뷰를 읽어 줍니다.")
    public ResponseEntity<ReviewDto> reviewRead(@PathVariable("reviewId") Long reviewId) {

        return ResponseEntity.ok(service.get(reviewId));
    }

    @PutMapping("/modify/{reviewId}")
    @ApiOperation(value = "하나의 리뷰 수정", notes = "하나의 리뷰를 수정 합니다.")
    public ResponseEntity<Map<String, String>> reviewModify(ReviewDto reviewDto) {

        ArrayList<MultipartFile> files = reviewDto.getFiles();

        files.forEach(f -> {
            log.info(f.getOriginalFilename());

            String uuid = UUID.randomUUID().toString();

            String saveName = uploadPath + File.separator + uuid + "_" + f.getOriginalFilename();

            String thumbnailSaveName = uploadPath + File.separator + "s_" + uuid + f.getOriginalFilename();

            try {
                FileCopyUtils.copy(f.getInputStream(),
                        new FileOutputStream(saveName, Boolean.parseBoolean(thumbnailSaveName)));
                Thumbnails.of(new File(saveName)).size(1000, 1000).outputFormat("jpg").toFile(thumbnailSaveName);

                ReviewFileDto fileDto = ReviewFileDto.builder().uuid(uuid).imgName(f.getOriginalFilename())
                        .path(uploadPath).build();
                reviewDto.addReviewFileDto(fileDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "sucess");

        service.modify(reviewDto);

        return new ResponseEntity(resultMap, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{reviewId}")
    @ApiOperation(value = "하나의 리뷰 삭제", notes = "하나의 리뷰를 삭제 합니다.")
    public ResponseEntity<String> reviewRemove(@PathVariable("reviewId") Long reviewId) {

        service.removeWithReplies(reviewId);

        return ResponseEntity.ok("delete success!!");
    }

}
