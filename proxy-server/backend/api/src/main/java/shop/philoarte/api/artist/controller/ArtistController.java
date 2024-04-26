package shop.philoarte.api.artist.controller;

import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.artist.domain.dto.ArtistDto;
import shop.philoarte.api.artist.domain.dto.ArtistFileDto;
import shop.philoarte.api.artist.service.fileService.ArtistFileServiceImpl;
import shop.philoarte.api.artist.domain.pageDomainDto.PageRequestDto;
import shop.philoarte.api.artist.domain.pageDomainDto.PageResultDto;
import shop.philoarte.api.artist.service.ArtistServiceImpl;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "artists")
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/artists", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class ArtistController {

    private final ArtistServiceImpl service;
    private final ArtistFileServiceImpl artistFileService;

    @Value("${shop.upload.path}")
    private String uploadPath;

    @GetMapping("/list/pages")
    public ResponseEntity<PageResultDto<ArtistDto, Object[]>> list(PageRequestDto page) {

        return new ResponseEntity(service.getPageList(page), HttpStatus.OK);
    }

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입 등록", notes = "회원 정보를 등록 합니다")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 422, message = "Artist - Username is alredy in use") })
    public ResponseEntity<Map<String, String>> signup(@Valid ArtistDto artistDto) throws IOException {

        ArrayList<MultipartFile> files = artistDto.getFiles();
        files.forEach(file -> {

            String uuid = UUID.randomUUID().toString();
            String saveName = uploadPath + File.separator + uuid + "_" + file.getOriginalFilename();
            String thumbnailSaveName = uploadPath + File.separator + uuid + "s_" + file.getOriginalFilename();

            try {
                FileCopyUtils.copy(file.getInputStream(),
                        new FileOutputStream(saveName, Boolean.parseBoolean(thumbnailSaveName)));
                Thumbnails.of(new File(saveName)).size(100, 100).outputFormat("jpg").toFile(thumbnailSaveName);

                ArtistFileDto fileDto = ArtistFileDto.builder().uuid(uuid).imgName(file.getOriginalFilename())
                        .path(uploadPath).build();

                artistDto.addArtistFileDto(fileDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Map<String, String> resultMap = new HashMap<>();
        return new ResponseEntity(service.signup(artistDto), HttpStatus.OK);

    }

    @PostMapping("/signin")
    @ApiOperation(value = "${ArtistController.signin}")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Somthing went wrong"),
            @ApiResponse(code = 422, message = "Invalid Artist-Username / Password supplied") })
    public ResponseEntity<ArtistDto> signin(@ApiParam("Signin Artist") @RequestBody ArtistDto artistDto)
            throws IOException {

        log.info(artistDto);
        return ResponseEntity.ok(service.signin(artistDto));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Artist>> findAll() {
        return ResponseEntity.ok(service.getAllData());
    }

    @GetMapping("/fetchOne/{artistId}")
    public ResponseEntity<Optional<Artist>> findById(@PathVariable("artistId") Long artistId) {
        return ResponseEntity.ok(service.findById(artistId));
    }

    @PutMapping("/update/{artistId}")
    public ResponseEntity<ArtistDto> updateById(@PathVariable("artistId") Long artistId,
            @RequestBody ArtistDto artist) {
        artist.setArtistId(artistId);
        return ResponseEntity.ok(service.updateById(artist));
    }

    @PutMapping("/mypage")
    public ResponseEntity<String> updateMypage(@RequestBody ArtistDto artistDto) {
        service.updateMypage(artistDto);
        return ResponseEntity.ok("Success Mypage");
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "${ArtistController.delete}")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 422, message = "Username is alredy in use") })
    public void deleteById(@RequestBody ArtistDto artistDto) {
        Long artistId = artistDto.getArtistId();
        service.deleteById(artistId);
    }

}
