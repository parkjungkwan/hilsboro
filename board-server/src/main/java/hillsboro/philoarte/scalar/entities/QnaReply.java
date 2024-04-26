package hillsboro.philoarte.scalar.entities;

import hillsboro.philoarte.scalar.abstracts.BaseEntity;
import lombok.*;

import javax.persistence.*;

@ToString(exclude ="qna")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="qnaReplys")
public class QnaReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="re_no")
    private Long reNo;

    @Column(name ="text")
    private String text;

    @Column(name = "replyer")
    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="qna_id")
    private Qna qna;
}