package shop.philoarte.api.qna.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.qna.domain.Qna;
import shop.philoarte.api.qna.domain.dto.QnaDto;
import shop.philoarte.api.qna.domain.dto.QnaPageRequestDto;
import shop.philoarte.api.qna.domain.dto.QnaPageResultDto;
import shop.philoarte.api.qna.repository.QnaReplyRepository;
import shop.philoarte.api.qna.repository.QnaRepository;
import shop.philoarte.api.review.domain.Review;
import shop.philoarte.api.review.domain.dto.PageRequestDto;
import shop.philoarte.api.review.domain.dto.PageResultDto;
import shop.philoarte.api.review.repository.ReplyRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
@Log4j2
@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

    private final QnaRepository repository;
    private final QnaReplyRepository replyRepository;

    @Override
    public Long save(QnaDto qnaDto) {

        Qna qna = dtoToEntity(qnaDto);
        repository.save(qna);
        return qna.getQnaId();
    }

    @Override
    public QnaDto get(Long qnaId) {
        List<Object[]> result = repository.getQnaWithReply(qnaId);
        Qna qna = (Qna) result.get(0)[0];
        Artist artist = (Artist) result.get(0)[1];
        Long replyCount = (Long) result.get(0)[2];
        return entityToDto(qna, artist, replyCount);
    }

    @Transactional
    @Override
    public void modify(QnaDto qnaDto) {

        Qna qna = repository.getOne(qnaDto.getQnaId());

        qna.changeTitle(qnaDto.getTitle());
        qna.changeContent(qnaDto.getContent());


        repository.save(qna);

    }

    @Transactional
    @Override
    public void removeWithQnaReplies(Long qnaId) {
        replyRepository.qnaReplyDelete(qnaId);
        repository.qnaDelete(qnaId);
    }


    @Override
    public QnaPageResultDto<QnaDto, Object[]> getList(QnaPageRequestDto qnaPageRequestDto) {

        log.info(qnaPageRequestDto);
        Function<Object[], QnaDto> fn = (arr -> entityToDto(
                (Qna) arr[0],
                (Artist) arr[1],
                (Long) arr[2]
        ));
        Page<Object[]> result = repository.searchPage(
                qnaPageRequestDto.getType(),
                qnaPageRequestDto.getKeyword(),
                qnaPageRequestDto.getPageable(Sort.by("qnaId").descending())

        );
        return new QnaPageResultDto<>(result, fn);
    }
}
