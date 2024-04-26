package hillsboro.philoarte.scalar.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import hillsboro.philoarte.scalar.abstracts.BaseEntity;
import hillsboro.philoarte.scalar.types.FundingDto;
import hillsboro.philoarte.scalar.providers.TokenProvider;
import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "fundings")
@ToString(exclude = { "artist", "fundingFiles" })
public class Funding extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_id")
    private Long fundingId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "goal_price")
    private long goalPrice;
    @Column(name = "view_cnt")
    private long viewCnt;
    @Column(name = "hashtag")
    private String hashtag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @JsonManagedReference
    @OneToMany(mappedBy = "funding", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<FundingFile> fundingFiles = new ArrayList<>();

    public static Funding of(FundingDto fundingDto) {
        return TokenProvider.getModelMapper().map(fundingDto, Funding.class);
    }

    public static Funding toDto(List<FundingDto> fundingDtos) {
        return TokenProvider.getModelMapper().map(fundingDtos, Funding.class);
    }

    // Dto -> Entity(Page)
    public static Page<Funding> of(Page<FundingDto> sourcePage) {
        return sourcePage.map(Funding::of);
    }

    public void saveRequest(FundingDto requestDto) {
        this.fundingId = requestDto.getFundingId();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.goalPrice = requestDto.getGoalPrice();
        this.hashtag = requestDto.getHashtag();
        this.artist = Artist.builder().artistId(requestDto.getArtistId()).build();

    }

    public Funding saveRequest(Funding toEntityRequest) {
        Funding funding = Funding.builder().fundingId(toEntityRequest.getFundingId())
                .content(toEntityRequest.getContent()).goalPrice(toEntityRequest.getGoalPrice())
                .hashtag(toEntityRequest.getHashtag()).build();
        return funding;

    }

    public Funding save(Funding toEntityRequest) {

        return null;
    }
}
