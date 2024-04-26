package hillsboro.philoarte.scalar.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.philoarte.api.qna.domain.dto.QnaDto;
import shop.philoarte.api.qna.domain.dto.QnaPageRequestDto;
import shop.philoarte.api.qna.domain.dto.QnaPageResultDto;
import shop.philoarte.api.qna.service.QnaServiceImpl;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Api(tags="qnas")
@RestController
@RequestMapping(value="/qnas")
public class QnaController {

    private final QnaServiceImpl qnaService;

    @PostMapping("/register")
    @ApiOperation(value="Q&A 게시글 등록", notes="Q&A 게시글을 등륵합니다")
    public ResponseEntity<Map<String, Long>> qnaSave(@RequestBody QnaDto qnaDto){

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("result", (qnaService.save(qnaDto)));

        return new ResponseEntity(resultMap, HttpStatus.OK);
    }

    @GetMapping("/list/pages")
    @ApiOperation(value="Q&A 게시글 목록", notes="Q&A 게시글의 목록을 보여줍니다.")
    public ResponseEntity<QnaPageResultDto<QnaDto, Object[]>> qnaList(QnaPageRequestDto qnaPageRequestDto){
        return ResponseEntity.ok(qnaService.getList(qnaPageRequestDto));
    }

    @GetMapping("/read/{qnaId}")
    @ApiOperation(value="하나의 Q&A 읽기", notes ="하나의 Q&A를 읽어 줍니다")
    public ResponseEntity<QnaDto> qnaRead(@PathVariable("qnaId") Long qnaId){
        return ResponseEntity.ok(qnaService.get(qnaId));
    }

    @PutMapping("/modify/{qnaId}")
    @ApiOperation(value="하나의 Q&A 수정", notes ="하나의 Q&A를 수정합니다")
    public ResponseEntity<Map<String, String>> qnaModify(@RequestBody QnaDto qnaDto){

        Map<String, String> resultMap = new HashMap<>();

        resultMap.put("result", "Modify Success");

        qnaService.modify(qnaDto);

        return new ResponseEntity(resultMap, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{qnaId}")
    @ApiOperation(value="하나의 Q&A 삭제", notes="하나의 Q&A를 삭제합니다.")
    public ResponseEntity<String> qnaRemove(@PathVariable("qnaId") Long qnaId){
        qnaService.removeWithQnaReplies(qnaId);

        return ResponseEntity.ok("delete success!!");
    }


}
