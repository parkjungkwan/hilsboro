package shop.philoarte.api;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import shop.philoarte.api.art.domain.Art;
import shop.philoarte.api.art.repository.ArtRepository;
import shop.philoarte.api.artist.domain.Artist;

@SpringBootTest
class ApiApplicationTests {
	@Autowired
	private ArtRepository repo;

	@Test
	void contextLoads() {
	}

	@Transactional
	@Commit
	@Test
	void getfiles() {

	}

}
