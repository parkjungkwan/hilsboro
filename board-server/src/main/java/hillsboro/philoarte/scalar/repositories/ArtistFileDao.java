package hillsboro.philoarte.scalar.repositories;

import hillsboro.philoarte.scalar.entities.ArtistFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ArtistFileDao extends JpaRepository<ArtistFile, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ArtistFile rf WHERE rf.artist.artistId = :artistId")
    void artistFileDelete(@Param("artistId") Long artistId);

}
