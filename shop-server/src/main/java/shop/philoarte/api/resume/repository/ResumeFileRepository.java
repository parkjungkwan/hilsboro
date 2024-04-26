package shop.philoarte.api.resume.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shop.philoarte.api.resume.domain.ResumeFile;

interface ResumeFileCustomRepository {
}

@Repository
@Transactional
public interface ResumeFileRepository extends JpaRepository<ResumeFile, Long>, ResumeFileCustomRepository {

    @Query("SELECT r from ResumeFile r WHERE r.resume.resumeId= :resumeId")
    List<ResumeFile> getAllForRemove(@Param("resumeId") Long resumeId);

    @Modifying
    @Query("DELETE from ResumeFile rf WHERE rf.resume.resumeId= :resumeId")
    void deleteByResumeId(@Param("resumeId") Long resumeId);
}
