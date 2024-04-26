package shop.philoarte.api.artist.service.artistTestService;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import shop.philoarte.api.artist.domain.QArtist;

public class ArtistPredicate {

    public static Predicate artistMultySearch(String username, String name, String email) {
        QArtist qArtist = QArtist.artist;

        BooleanBuilder builder = new BooleanBuilder();

        if (username != null) {
            builder.and(qArtist.username.eq(username));
        }

        if (name != null) {
            builder.and(qArtist.name.eq(name));
        }

        if (email != null) {
            builder.and(qArtist.email.eq(email));
        }

        return builder;
    }

}
