package philoarte.jaemin.api.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import philoarte.jaemin.api.artist.domain.Artist;

@Repository
public interface ArtistRespository extends JpaRepository<Artist, Long> {

}