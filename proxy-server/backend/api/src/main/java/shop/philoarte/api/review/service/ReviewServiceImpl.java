package shop.philoarte.api.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.review.domain.Review;
import shop.philoarte.api.review.domain.ReviewFile;
import shop.philoarte.api.review.domain.dto.PageRequestDto;
import shop.philoarte.api.review.domain.dto.PageResultDto;
import shop.philoarte.api.review.domain.dto.ReviewDto;
import shop.philoarte.api.review.repository.ReplyRepository;
import shop.philoarte.api.review.repository.ReviewFileRepository;
import shop.philoarte.api.review.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repository;
    private final ReviewFileRepository reviewFileRepository;
    private final ReplyRepository replyRepository;


    @Transactional
    @Override
    public Long save(ReviewDto reviewDto) {
        Map<String, Object> entityMap = dtoToEntity(reviewDto);
        Review review = (Review) entityMap.get("review");
        List<ReviewFile> reviewFileList = (List<ReviewFile>) entityMap.get("fileList");

        repository.save(review);

        if (reviewFileList != null && reviewFileList.size() > 0) {
            reviewFileList.forEach(reviewFile -> {
                reviewFileRepository.save(reviewFile);
            });
        }

        return review.getReviewId();
    }

    @Override
    public ReviewDto get(Long reviewId) {

        List<Object[]> result = repository.getRevieWithReply(reviewId);
        Review review = (Review) result.get(0)[0];
        Artist artist = (Artist) result.get(0)[1];

        List<ReviewFile> reviewFileList = new ArrayList<>();

        Long replyCount = (Long) result.get(0)[2];

        result.forEach(arr -> {
            ReviewFile reviewFile = (ReviewFile) arr[3];
            reviewFileList.add(reviewFile);
        });

        return entityToDto(review, artist, replyCount, reviewFileList);
    }

    @Transactional
    @Override
    public void modify(ReviewDto reviewDto) {
        Map<String, Object> entityMap = dtoToEntity(reviewDto);
        Review review = repository.getOne(reviewDto.getReviewId());

        review.changeTitle(reviewDto.getTitle());
        review.changeContent(reviewDto.getContent());

        repository.save(review);

        // 기존 파일 삭제

            reviewFileRepository.reviewFileDelete(review.getReviewId());


        if (entityMap.get("fileList") != null && ((List<ReviewFile>) entityMap.get("fileList")).size() > 0) {

            List<ReviewFile> reviewFileList = (List<ReviewFile>) entityMap.get("fileList");
            reviewFileList.forEach(reviewFile -> {
                reviewFileRepository.save(reviewFile);
            });

        }

    }

    @Transactional
    @Override
    public void removeWithReplies(Long reviewId) {
        reviewFileRepository.reviewFileDelete(reviewId);
        replyRepository.replyDelete(reviewId);
        repository.reviewDelete(reviewId);

    }

    @Override
    public PageResultDto<ReviewDto, Object[]> getList(PageRequestDto pageRequestDto) {
        log.info(pageRequestDto);

//        Pageable pageable = pageRequestDto.getPage(Sort.by("reviewId").descending());
        Function<Object[], ReviewDto> fn = (arr -> entityToDto(
                (Review) arr[0],
                (Artist) arr[1],
                (Long) arr[2],
                (List<ReviewFile>) (Arrays.asList((ReviewFile) arr[3]))));


        Page<Object[]> result = repository.searchPage(
                pageRequestDto.getType(),
                pageRequestDto.getKeyword(),
                pageRequestDto.getPageable(Sort.by("reviewId").descending())

        );


        return new PageResultDto<>(result, fn);
    }


}
