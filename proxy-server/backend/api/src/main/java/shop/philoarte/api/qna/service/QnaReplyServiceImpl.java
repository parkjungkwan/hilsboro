package shop.philoarte.api.qna.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.philoarte.api.qna.domain.Qna;
import shop.philoarte.api.qna.domain.QnaReply;
import shop.philoarte.api.qna.domain.dto.QnaReplyDto;
import shop.philoarte.api.qna.repository.QnaReplyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QnaReplyServiceImpl implements QnaReplyService {

    private final QnaReplyRepository repository;

    @Override
    public Long save(QnaReplyDto qnaReplyDto) {

        QnaReply qnaReplySave = dtoToEntity(qnaReplyDto);

        repository.save(qnaReplySave);

        return qnaReplyDto.getReNo();
    }

    @Override
    public List<QnaReplyDto> getList(Long qnaId) {
        List<QnaReply> result = repository.getQnaRepliesByQnaOrderByRegDate(Qna.builder().qnaId(qnaId).build());
        return result.stream().map(qnaReply -> entityToDto(qnaReply)).collect(Collectors.toList());
    }

    @Override
    public void modify(QnaReplyDto qnaReplyDto) {
        QnaReply qnaReply = dtoToEntity(qnaReplyDto);

        repository.save(qnaReply);
    }

    @Override
    public void remove(Long reNo) {
        repository.deleteById(reNo);
    }


}
