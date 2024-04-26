package shop.philoarte.api.funding.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import shop.philoarte.api.funding.domain.FundingDto;
import shop.philoarte.api.funding.service.FundingServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RestController
@RequiredArgsConstructor
@Api(tags = "funding")
@RequestMapping("/funding")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FundingController {
    private final FundingServiceImpl service;

    @GetMapping("/{fundingId}")
    public ResponseEntity<FundingDto> getOneFundingById(@PathVariable("fundingId") Long fundingId) {
        return ResponseEntity.ok(service.getFundingById(fundingId));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> save(@RequestBody FundingDto fundingDto) {

        return ResponseEntity.ok("등록을 성공했습니다." + service.save(fundingDto));
    }

    @PutMapping("/edit/{fundingId}")
    public ResponseEntity<String> updateFunding(@PathVariable("fundingId") Long fundingId,
            @RequestBody FundingDto fundingdto) {
        if (service.getFundingById(fundingId) == null) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("수정 성공" + service.save(fundingdto));
    }

    @DeleteMapping("/{fundingId}")
    public ResponseEntity<String> deleteFunding(@PathVariable("fundingId") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("식제 성공");
    }

    @GetMapping("/search/{hashtag}")
    public ResponseEntity<List<FundingDto>> geetFundingByHash(@RequestBody FundingDto dto,
            @PathVariable("hashtag") String hashtag) {
        return ResponseEntity.ok(service.getListByHashtag(dto, hashtag));
    }

}
