package hillsboro.philoarte.scalar.repositories;

import java.util.List;

import javax.transaction.Transactional;

import hillsboro.philoarte.scalar.entities.ResumeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
@Transactional
public interface ResumeFileDao extends JpaRepository<ResumeFile, Long> {

    @Query("SELECT r from ResumeFile r WHERE r.resume.resumeId= :resumeId")
    List<ResumeFile> getAllForRemove(@Param("resumeId") Long resumeId);

    @Modifying
    @Query("DELETE from ResumeFile rf WHERE rf.resume.resumeId= :resumeId")
    void deleteByResumeId(@Param("resumeId") Long resumeId);
}
