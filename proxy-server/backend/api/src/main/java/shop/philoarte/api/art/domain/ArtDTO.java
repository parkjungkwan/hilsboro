package shop.philoarte.api.art.domain;

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
public class ArtDTO {

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
    private ArtFileDTO file;

    private List<ArtFileDTO> files;

}
