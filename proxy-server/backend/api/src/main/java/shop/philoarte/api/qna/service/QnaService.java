package shop.philoarte.api.qna.service;

import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.qna.domain.Qna;
import shop.philoarte.api.qna.domain.dto.QnaDto;
import shop.philoarte.api.qna.domain.dto.QnaPageRequestDto;
import shop.philoarte.api.qna.domain.dto.QnaPageResultDto;
import shop.philoarte.api.review.domain.dto.PageRequestDto;
import shop.philoarte.api.review.domain.dto.PageResultDto;
import shop.philoarte.api.review.domain.dto.ReviewDto;

import java.util.HashMap;
import java.util.Map;

public interface QnaService {
    Long save(QnaDto qnaDto);

    QnaDto get(Long qnaId);

    void modify(QnaDto qnaDto);

    void removeWithQnaReplies(Long qnaId);

    QnaPageResultDto<QnaDto, Object[]> getList(QnaPageRequestDto qnaPageRequestDto);

    default Qna dtoToEntity(QnaDto qnaDto){

        Artist artists = Artist.builder().artistId(qnaDto.getWriterId()).build();
        Qna qna = Qna.builder()
                .qnaId(qnaDto.getQnaId())
                .title(qnaDto.getTitle())
                .content(qnaDto.getContent())
                .artist(artists)
                .build();

        return qna;
    }

    default QnaDto entityToDto(Qna qna, Artist artists, Long replyCount) {
        QnaDto qnaDto = QnaDto.builder()
                .qnaId(qna.getQnaId())
                .title(qna.getTitle())
                .content(qna.getContent())
                .regDate(qna.getRegDate())
                .modDate(qna.getModDate())
                .writerId(artists==null ? 1L : artists.getArtistId())
                .writerName(artists == null ? "" : artists.getName())
                .replyCount(replyCount.intValue())
                .build();
        qnaDto.setReplyCount(replyCount.intValue());
        return qnaDto;
    }
}
