package shop.philoarte.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.artist.repository.ArtistRepository;

import java.util.stream.IntStream;

@SpringBootTest
public class ArtistRepositoryTests {

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    public void insertsArtists(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Artist artist = Artist.builder()
                    .username("user"+i+"id")
                    .password("1234")
                    .name("내 이름" + i)
                    .address("서울시")
                    .email("이메일")
                    .phoneNumber("010-0101-01010")
                    .build();
            artistRepository.save(artist);
        });
    }
}