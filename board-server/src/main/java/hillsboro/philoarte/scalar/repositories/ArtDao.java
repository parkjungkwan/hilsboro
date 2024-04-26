package hillsboro.philoarte.scalar.repositories;

import hillsboro.philoarte.scalar.customs.ArtCustom;
import hillsboro.philoarte.scalar.entities.Art;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtDao extends JpaRepository<Art, Long>, ArtCustom {

    @Query("SELECT a, a.artist, a.category, a.resume, f FROM Art a LEFT JOIN ArtFile f ON f.art = a WHERE a.artId = :artId")
    List<Object[]> getArtByArtId(@Param("artId") Long artId);

    @Query("SELECT a, a.artist, a.category, a.resume, f FROM Art a LEFT JOIN ArtFile f ON f.art = a WHERE f.repImg = true")
    Page<Object[]> getArts(Pageable pageable);

    @Query("SELECT DATE_FORMAT(reg_date, '%Y-%m-%d') AS date, count(*) AS count FROM Art WHERE artist.artistId = :artistId GROUP BY DATE_FORMAT(reg_date, '%Y-%m-%d') ORDER BY date DESC")
    List<Object[]> countByArtistId(@Param("artistId") Long artistId);

    @Query("SELECT a, a.artist, a.category, a.resume, f FROM Art a LEFT JOIN ArtFile f ON f.art = a WHERE a.artist.artistId = :artistId AND f.repImg = true")
    Page<Object[]> getArtsByArtistId(Pageable pageable, @Param("artistId") Long artistId);




    @EntityGraph(attributePaths = {"artist", "category", "resume"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT a, a.artist, a.category, a.resume FROM Art a ORDER BY a.artId DESC")
    List<Object[]> getArtsTest(Pageable pageable);

}
