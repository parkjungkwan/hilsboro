package shop.philoarte.api.review.domain;

import lombok.*;
import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.common.domain.BaseEntity;
import shop.philoarte.api.common.domain.BaseEntity;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
@ToString(exclude = {"artist, art"})
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

}
