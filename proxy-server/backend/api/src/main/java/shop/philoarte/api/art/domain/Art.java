package shop.philoarte.api.art.domain;

import lombok.*;
import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.category.domain.Category;
import shop.philoarte.api.common.domain.BaseEntity;
import shop.philoarte.api.resume.domain.Resume;

import javax.persistence.*;

@Getter
@ToString(exclude = {"artist", "category", "resume"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "arts")
public class Art extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long artId;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String mainImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

}
