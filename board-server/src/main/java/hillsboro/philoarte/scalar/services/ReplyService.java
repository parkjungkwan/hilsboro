package hillsboro.philoarte.scalar.services;


import hillsboro.philoarte.scalar.entities.Reply;
import hillsboro.philoarte.scalar.entities.Review;
import hillsboro.philoarte.scalar.types.ReplyDto;

import java.util.List;

public interface ReplyService {

    Long save(ReplyDto replyDto);

    void remove(Long rno);

    void modify(ReplyDto replyDto);


    List<ReplyDto> getList(Long reviewId);

    default Reply dtoToEntity(ReplyDto replyDto) {
        Review review = Review.builder().reviewId(replyDto.getReviewId()).build();

        Reply reply = Reply.builder()
                .rno(replyDto.getRno())
                .text(replyDto.getText())
                .replyer(replyDto.getReplyer())
                .uuid(replyDto.getUuid())
                .imageName(replyDto.getImgName())
                .path(replyDto.getPath())
                .review(review)
                .build();

        return reply;
    }

    default ReplyDto entityToDto(Reply reply) {
        ReplyDto replyDto = ReplyDto.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .uuid(reply.getUuid())
                .imgName(reply.getImageName())
                .path(reply.getPath())
                .reviewId(reply.getReview().getReviewId())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();

        return replyDto;
    }

}
