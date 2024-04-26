package shop.philoarte.api.resume.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import shop.philoarte.api.artist.domain.QArtist;
import shop.philoarte.api.category.domain.QCategory;
import shop.philoarte.api.resume.domain.QResume;
import shop.philoarte.api.resume.domain.QResumeFile;
import shop.philoarte.api.resume.domain.Resume;

@Log4j2
public class SearchResumeRepositoryImpl extends QuerydslRepositorySupport implements SearchResumeRepository {

    public SearchResumeRepositoryImpl() {
        super(Resume.class);
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        QResume resume = QResume.resume;
        QArtist artist = QArtist.artist;
        QCategory category = QCategory.category;
        QResumeFile resumeFile = QResumeFile.resumeFile;
        JPQLQuery<Resume> jpqlQuery = from(resume);
        jpqlQuery.leftJoin(artist).on(resume.artist.eq(artist));
        jpqlQuery.leftJoin(category).on(resume.category.eq(category));
        jpqlQuery.leftJoin(resumeFile).on(resumeFile.resume.eq(resume));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(resume, artist, category, resumeFile, resume.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = resume.resumeId.gt(0L);

        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "u":
                        conditionBuilder.or(artist.username.contains(keyword));
                        break;
                    case "n":
                        conditionBuilder.or(artist.name.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(category.categoryName.contains(keyword));
                        break;
                    case "t":
                        conditionBuilder.or(resume.title.contains(keyword));
                        break;
                    case "d":
                        conditionBuilder.or(resume.detail.contains(keyword));
                        break;
                }

            }
            booleanBuilder.and(conditionBuilder);

        }
        tuple.where(booleanBuilder);

        Sort sort = pageable.getSort();

        // tuple.orderBy(resume.resumeId.desc());

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderByExpression = new PathBuilder(Resume.class, "resume");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
        tuple.groupBy(resume);
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();

        log.info("count: " + count);

        return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable,
                count);
    }

}
