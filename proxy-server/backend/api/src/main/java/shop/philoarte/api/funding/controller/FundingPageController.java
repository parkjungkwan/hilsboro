package shop.philoarte.api.funding.controller;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import shop.philoarte.api.funding.domain.Funding;
import shop.philoarte.api.funding.domain.FundingDto;
import shop.philoarte.api.funding.domain.FundingPageDto;
import shop.philoarte.api.funding.domain.PageRequestDto;
import shop.philoarte.api.funding.service.FundingServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Api(tags = "funding")
@RequestMapping("/funding")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FundingPageController {
    private final FundingServiceImpl service;

    @GetMapping("/list")
    public ResponseEntity<FundingPageDto<FundingDto, Funding>> list(int page) {

        return ResponseEntity.ok(service.getList(page));
    }

    @GetMapping("/list/{fundingId}")
    public ResponseEntity<FundingPageDto<FundingDto, Funding>> getByFundingId(PageRequestDto requestDto,
            @PathVariable("fundingId") Long fundingId) {
        return ResponseEntity.ok(service.getPageById(requestDto, fundingId));
    }

    @GetMapping("/list/artistfunding/{artistId}")
    public ResponseEntity<FundingPageDto<FundingDto, Funding>> getByArtistId(PageRequestDto requestDto,
            @PathVariable("artistId") Long artistId) {
        return ResponseEntity.ok(service.getPageByArtistId(requestDto, artistId));
    }

    @GetMapping("/list/search")
    public ResponseEntity<FundingPageDto<FundingDto, Funding>> searchTCInPage(PageRequestDto requestDto,
            @RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(service.searchTitleAndContent(requestDto, keyword));
    }

    @GetMapping("/list/artist")
    public ResponseEntity<FundingPageDto<FundingDto, Funding>> searchArtistName(PageRequestDto requestDto,
            @RequestParam("artistName") String artistName) {
        return ResponseEntity.ok(service.getByartistName(requestDto, artistName));
    }


}