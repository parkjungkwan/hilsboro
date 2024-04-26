package hillsboro.philoarte.scalar.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.philoarte.api.qna.domain.dto.QnaReplyDto;
import shop.philoarte.api.qna.service.QnaReplyServiceImpl;

import java.util.List;

@Log4j2
@CrossOrigin(origins = "*")
@Api(tags = "qreplies")
@RequiredArgsConstructor
@RequestMapping("/qreplies")
@RestController
public class QnaReplyController {

    private final QnaReplyServiceImpl qnaReplyService;

    @PostMapping("/register")
    @ApiOperation(value = "Q&A 댓글 등록", notes = "Q&A 댓글을 등록합니다.")
    public ResponseEntity<String> qnaReplySave(@RequestBody QnaReplyDto qnaReplyDto) {
        qnaReplyService.save(qnaReplyDto);

        return ResponseEntity.ok("success");
    }

    @GetMapping("/list/{reNo}")
    @ApiOperation(value = "Q&A 댓글 목록", notes = "Q&A 댓글 목록을 보여줍니다.")
    public ResponseEntity<List<QnaReplyDto>> qnaReplyList(@PathVariable("reNo") Long reNo) {

        log.info("qnaId : " + reNo);

        return ResponseEntity.ok(qnaReplyService.getList(reNo));
    }

    @PutMapping("/modify/{reNo}")
    @ApiOperation(value = "하나의 Q&A 댓글 수정", notes = "하나의 Q&A 댓글을 수정합니다.")
    public ResponseEntity<String> qnaReplyModify(@RequestBody QnaReplyDto qnaReplyDto) {
        qnaReplyService.modify(qnaReplyDto);

        return ResponseEntity.ok("Success Modify");
    }

    @DeleteMapping("/remove/{reNo}")
    @ApiOperation(value = "하나의 Q&A 댓글 삭제", notes = "하나의 Q&A 댓글을 삭제 합니다")
    public ResponseEntity<String> qnaReplyRemove(@PathVariable("reNo") Long reNo) {
        qnaReplyService.remove(reNo);

        return ResponseEntity.ok("Delete Success!!");
    }
}
