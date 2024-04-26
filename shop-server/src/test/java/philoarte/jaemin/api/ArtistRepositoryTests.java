package philoarte.jaemin.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import philoarte.jaemin.api.artist.domain.Artist;
import philoarte.jaemin.api.artist.repository.ArtistRepository;

import java.util.stream.IntStream;

@SpringBootTest
public class ArtistRepositoryTests {

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    public void insertsArtists() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            Artist artist = Artist.builder()
                    .username("user" + i + "id")
                    .password("1234")
                    .name("내 이름" + 1)
                    .build();

            artistRepository.save(artist);
        });
    }
}
