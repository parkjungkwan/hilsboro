package hillsboro.philoarte.scalar.customImpls;

import hillsboro.philoarte.scalar.entities.Qna;
import hillsboro.philoarte.scalar.customs.QnaCustom;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class QnaCustomImpl extends QuerydslRepositorySupport implements QnaCustom {

    public QnaCustomImpl() {
        super(Qna.class);
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        log.info("searchPage...........");
        return null;
    }

    @Override
    public Qna search() {
        log.info("search1...........");


        return null;
    }
}
