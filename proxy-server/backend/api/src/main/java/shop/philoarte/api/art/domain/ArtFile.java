package shop.philoarte.api.art.domain;

import lombok.*;
import shop.philoarte.api.common.domain.BaseEntity;

import javax.persistence.*;

@Getter
@ToString(exclude = "art")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "art_files")
public class ArtFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long fileId;

    @Column
    private String uuid;

    @Column
    private String originalFileName;

    @Column
    private String savedFileName;

    @Column
    private String workedDate;

    @Column
    private Boolean repImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_id")
    private Art art;

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public void setArt(Art art) {
        this.art = art;
    }

}
