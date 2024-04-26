package hillsboro.philoarte.scalar.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import hillsboro.philoarte.scalar.abstracts.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "resumes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString(exclude = { "artist", "category", "resumeFiles" })
public class Resume extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long resumeId;
    @Column(name = "title")
    private String title;
    @Column(name = "self_introduce", columnDefinition = "LONGTEXT")
    private String selfIntroduce;
    @Column(name = "detail", columnDefinition = "LONGTEXT")
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonManagedReference
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<ResumeFile> resumeFiles = new ArrayList<>();

    public void changeTitle(String title) {
        this.title = title;
    }

    public static Resume of(ResumeDto resumeDto) {
        Resume resume = ModelMapperUtils.getModelMapper().map(resumeDto, Resume.class);
        return resume;
    }

    public void saveAll(ResumeDto resumeDto) {
        this.resumeId = resumeDto.getResumeId();
        this.title = resumeDto.getTitle();
        this.selfIntroduce = resumeDto.getSelfIntroduce();
        this.detail = resumeDto.getDetail();
        this.artist = Artist.builder().artistId(resumeDto.getArtistId()).username(resumeDto.getUsername())
                .name(resumeDto.getName()).build();
        this.category = Category.builder().categoryId(resumeDto.getCategoryId())
                .categoryName(resumeDto.getCategoryName()).build();

    }

}
