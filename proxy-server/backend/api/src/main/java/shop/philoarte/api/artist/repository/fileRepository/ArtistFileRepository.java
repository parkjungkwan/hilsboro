package shop.philoarte.api.artist.repository.fileRepository;

import shop.philoarte.api.artist.domain.ArtistFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ArtistFileRepository extends JpaRepository<ArtistFile, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ArtistFile rf WHERE rf.artist.artistId = :artistId")
    void artistFileDelete(@Param("artistId") Long artistId);

}
