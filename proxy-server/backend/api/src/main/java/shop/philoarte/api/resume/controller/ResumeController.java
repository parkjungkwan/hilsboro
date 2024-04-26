package shop.philoarte.api.resume.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import shop.philoarte.api.resume.domain.Resume;
import shop.philoarte.api.resume.domain.ResumeDto;
import shop.philoarte.api.resume.domain.page.PageResultDto;
import shop.philoarte.api.resume.service.ResumeFileServiceImpl;
import shop.philoarte.api.resume.service.ResumeServiceImpl;

@Api
@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ResumeController {

    private final ResumeServiceImpl service;
    private final ResumeFileServiceImpl fileService;

    @PostMapping("/register")
    @ApiOperation(value = "${ResumeController.register}")
    public ResponseEntity<String> save(@RequestBody ResumeDto resume) {
        return ResponseEntity.ok(service.resumeSaveWithFile(resume));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "${ResumeController.edit}")
    public ResponseEntity<String> editResume(@RequestBody ResumeDto resume) {
        return ResponseEntity.ok(service.editResume(resume));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "${ResumeController.delete}")
    public ResponseEntity<String> delete(@RequestBody ResumeDto resume) {
        fileService.removeFiles(resume.getResumeId());
        return ResponseEntity.ok(service.delete(resume));
    }

    @GetMapping("/read/{resumeId}")
    @ApiOperation(value = "${ResumeController.read}")
    public ResponseEntity<ResumeDto> findById(@PathVariable("resumeId") Long resumeId) {
        return ResponseEntity.ok(service.getById(resumeId));
    }

    // search for Developers
    @GetMapping("/list_page")
    public ResponseEntity<PageResultDto<ResumeDto, Resume>> getAllDataPaging(int page) {

        return ResponseEntity.ok(service.getAllDataPaging(page));
    }

    @GetMapping("/list_all")
    public ResponseEntity<List<ResumeDto>> getAllData() {

        return ResponseEntity.ok(service.getAllResume());
    }

    @GetMapping("/list_by_userid/{artistId}")
    public ResponseEntity<PageResultDto<ResumeDto, Resume>> getUserPKDataPage(@PathVariable("artistId") Long artistId,
            int page) {
        return ResponseEntity.ok(service.getUserPKDataPage(artistId, page));
    }

    @GetMapping("/list_by_categoryid/{categoryId}")
    public ResponseEntity<PageResultDto<ResumeDto, Resume>> getCategoryPKDataPage(
            @PathVariable("categoryId") Long categoryId, int page) {

        return ResponseEntity.ok(service.getCategoryPKDataPage(categoryId, page));
    }

    @GetMapping("/list_by_u_and_c_id/{categoryId},{artistId}")
    public ResponseEntity<PageResultDto<ResumeDto, Resume>> getCategoryAndUserDataPage(
            @PathVariable("categoryId") Long categoryId, @PathVariable("artistId") Long artistId, int page) {

        return ResponseEntity.ok(service.getCategoryAndUserDataPage(categoryId, artistId, page));
    }

    @GetMapping(value = { "/search" })
    public ResponseEntity<PageResultDto<ResumeDto, Object[]>> conditionSearch(String type, String keyword, int page) {
        return ResponseEntity.ok(service.conditionSearch(type, keyword, page));
    }

    @GetMapping("/count/{artistId}")
    public ResponseEntity<List<Object[]>> count(@PathVariable("artistId") Long artistId) {
        return ResponseEntity.ok(service.countByArtistId(artistId));
    }

}
