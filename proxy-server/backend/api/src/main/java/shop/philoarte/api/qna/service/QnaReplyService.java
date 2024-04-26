package shop.philoarte.api.qna.service;

import shop.philoarte.api.qna.domain.Qna;
import shop.philoarte.api.qna.domain.QnaReply;
import shop.philoarte.api.qna.domain.dto.QnaReplyDto;

import java.util.List;

public interface QnaReplyService {
    Long save(QnaReplyDto qnaReplyDto);

    void remove(Long reNo);

    void modify(QnaReplyDto  qnaReplyDto);

    List<QnaReplyDto> getList(Long qnaId);

    default QnaReply dtoToEntity(QnaReplyDto qnaReplyDto){

        Qna qna = Qna.builder().qnaId(qnaReplyDto.getQnaId()).build();

        QnaReply qnaReply = QnaReply.builder()
                .reNo(qnaReplyDto.getReNo())
                .text(qnaReplyDto.getText())
                .replyer(qnaReplyDto.getReplyer())
                .qna(qna)
                .build();

        return qnaReply;
    }

    default QnaReplyDto entityToDto(QnaReply qnaReply){
        QnaReplyDto qnaReplyDto = QnaReplyDto.builder()
                .reNo(qnaReply.getReNo())
                .text(qnaReply.getText())
                .replyer(qnaReply.getReplyer())
                .qnaId(qnaReply.getQna().getQnaId())
                .regDate(qnaReply.getRegDate())
                .modDate(qnaReply.getModDate())
                .build();

        return qnaReplyDto;
    }
}
