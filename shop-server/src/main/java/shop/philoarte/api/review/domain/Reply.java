package shop.philoarte.api.review.domain;

import lombok.*;
import shop.philoarte.api.common.domain.BaseEntity;
import shop.philoarte.api.common.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "replys")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "review")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rno")
    private Long rno;
    @Column(name = "text")
    private String text;
    @Column(name = "replyer")
    private String replyer;


    private String uuid;

    @Column(name = "img_name")
    private String imageName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;


}
