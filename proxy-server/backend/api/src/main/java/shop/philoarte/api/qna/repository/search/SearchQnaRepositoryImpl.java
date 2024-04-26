package shop.philoarte.api.qna.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import shop.philoarte.api.artist.domain.QArtist;
import shop.philoarte.api.qna.domain.QQna;
import shop.philoarte.api.qna.domain.QQnaReply;
import shop.philoarte.api.qna.domain.Qna;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchQnaRepositoryImpl extends QuerydslRepositorySupport implements SearchQnaRepository {

    public SearchQnaRepositoryImpl() {
        super(Qna.class);
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        log.info("searchPage...........");

        QQna qna = QQna.qna;

        QArtist artist = QArtist.artist;

        QQnaReply qnaReply = QQnaReply.qnaReply;

        JPQLQuery<Qna> jpqlQuery = from(qna);

        jpqlQuery.leftJoin(artist).on(qna.artist.eq(artist));
        jpqlQuery.leftJoin(qnaReply).on(qnaReply.qna.eq(qna));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(qna, artist, qnaReply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        BooleanExpression expression = qna.qnaId.gt(0L);

        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");

            log.info(Arrays.toString(typeArr));

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "t":
                        conditionBuilder.or(qna.title.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(artist.name.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(qna.content.contains(keyword));
                        break;

                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        tuple.where(booleanBuilder);

        //order by
        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Qna.class, "qna");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(qna);

        //page 처리
        log.info(pageable.getOffset());
        log.info(pageable.getPageSize());

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();
        log.info("COUNT : " + count);


        return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }

    @Override
    public Qna search() {
        log.info("search1...........");

        QQna qna = QQna.qna;
        QQnaReply qnaReply = QQnaReply.qnaReply;
        QArtist artist = QArtist.artist;

        JPQLQuery<Qna> jpqlQuery = from(qna);
        jpqlQuery.leftJoin(artist).on(qna.artist.eq(artist));
        jpqlQuery.leftJoin(qnaReply).on(qnaReply.qna.eq(qna));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(qna, artist.name, qnaReply.count());
        tuple.groupBy(qna);

        List<Tuple> result = tuple.fetch();
        log.info(result);
        return null;
    }
}
