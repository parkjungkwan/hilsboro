package hillsboro.philoarte.scalar.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtFileDto {

    // ArtFile
    private Long fileId;

    private String uuid;

    private String originalFileName;

    private String savedFileName;

    private String workedDate;

    private Boolean repImg;

    // Art
    private ArtDto art;

}
