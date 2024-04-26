package hillsboro.philoarte.scalar.types;

import lombok.*;
import shop.philoarte.api.artist.domain.dto.ArtistDto;
import shop.philoarte.api.category.domain.CategoryDto;
import shop.philoarte.api.resume.domain.ResumeDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtDto {

    // Art
    private Long artId;

    private String title;

    private String description;

    private String mainImg;

    private LocalDateTime regDate;

    // Artist
    private ArtistDto artist;

    // Category
    private CategoryDto category;

    // Resume
    private ResumeDto resume;

    // ArtFile
    private ArtFileDto file;

    private List<ArtFileDto> files;

}
