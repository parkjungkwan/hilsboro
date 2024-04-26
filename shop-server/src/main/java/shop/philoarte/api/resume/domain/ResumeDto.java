package shop.philoarte.api.resume.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.category.domain.Category;
import shop.philoarte.api.common.util.ModelMapperUtils;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ResumeDto {

    private Long resumeId;
    private String title;
    private String selfIntroduce;
    private String detail;
    private Long artistId;
    private String username;
    private String name;
    private Long categoryId;
    private String categoryName;

    private List<ResumeFileDto> resumeFiles;

    public static ResumeDto of(Resume resume) {
        ResumeDto resumeDto = ModelMapperUtils.getModelMapper().map(resume, ResumeDto.class);
        return resumeDto;
    }

}
