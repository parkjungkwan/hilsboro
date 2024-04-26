package hillsboro.philoarte.scalar.types;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
