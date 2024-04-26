package hillsboro.philoarte.scalar.repositories;

import hillsboro.philoarte.scalar.customs.ResumeCustom;
import hillsboro.philoarte.scalar.entities.Resume;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeDao extends JpaRepository<Resume, Long>, ResumeCustom {

    @EntityGraph(attributePaths = { "artist", "category", "artist.roles" }, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Resume> findById(@Param("resumeId") Long resumeId);

    @EntityGraph(attributePaths = { "artist", "category", "artist.roles" }, type = EntityGraph.EntityGraphType.FETCH)
    List<Resume> findAll();

}