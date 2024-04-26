package hillsboro.philoarte.scalar.customImpls;

import hillsboro.philoarte.scalar.entities.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ResumeCustomImpl extends QuerydslRepositorySupport {

    public ResumeCustomImpl() {
        super(Resume.class);
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        return null;
    }

}
