package hillsboro.philoarte.scalar.customs;

import hillsboro.philoarte.scalar.entities.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResumeCustom {

    @EntityGraph(attributePaths = { "artist", "category", "artist.roles" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Resume r ORDER BY r.resumeId desc")
    List<Resume> getAllResume();

    @EntityGraph(attributePaths = { "artist", "category", "artist.roles" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Resume r ORDER BY r.resumeId desc ")
    Page<Resume> getAllDataPaging(Pageable pageable);

    @EntityGraph(attributePaths = { "category", "artist.roles" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Resume r  WHERE r.artist.artistId= :artistId")
    Page<Resume> getUserPKDataPage(@Param("artistId") Long artistId, Pageable pageable);

    @EntityGraph(attributePaths = { "artist", "category", "artist.roles" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Resume r WHERE r.category.categoryId=:categoryId")
    Page<Resume> getCategoryPKDataPage(@Param("categoryId") Long categoryId, Pageable pageable);

    @EntityGraph(attributePaths = { "artist", "category", "artist.roles" }, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Resume r WHERE r.category.categoryId= :categoryId AND r.artist.artistId = :artistId")
    Page<Resume> getCategoryAndUserDataPage(@Param("categoryId") Long categoryId, @Param("artistId") Long artistId,
                                            Pageable pageable);

    @Query("SELECT DATE_FORMAT(reg_date, '%y-%m-%d') AS date, count(*) AS cnt FROM Resume WHERE artist.artistId= :artistId GROUP BY DATE_FORMAT(reg_date, '%Y%m%d') ORDER BY date DESC")
    List<Object[]> countByArtistId(@Param("artistId") Long artistId);

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
