package shop.philoarte.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.qna.domain.Qna;
import shop.philoarte.api.qna.repository.QnaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class QnaRepositoryTests {

    @Autowired
    private QnaRepository qnaRepository;

    @Test
    public void insertQna(){
        IntStream.rangeClosed(1,30).forEach(i->{
            Artist artist = Artist.builder()
                    .artistId(1L+i)
                    .build();

            Qna qna = Qna.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .artist(artist)
                    .build();

            qnaRepository.save(qna);

        });
    }

    @Transactional
    @Test
    public void testRead(){
        Optional<Qna> result = qnaRepository.findById(3L);

        Qna qna = result.get();

        System.out.println(qna);
        System.out.println(qna.getQnaId());
    }

    @Test
    public void testReadWithArtist(){
        Object result = qnaRepository.getQnaWithWriter(1L);

        Object [] arr = (Object[]) result;

        System.out.println("--------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testWithQnaReplyCount(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("qnaId").descending());

        Page<Object[]> result = qnaRepository.getQnaWithReplyCount(pageable);

                result.get().forEach(row->{
                    Object[] arr = (Object[]) row;

                    System.out.println("-------------------");
                    System.out.println(Arrays.toString(arr));
                });

    }

    @Test
    public void testRead2(){
        Object result = qnaRepository.getQnaByQnaId(10L);

        Object [] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSearchPage(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("qnaId").descending());

        Page<Object[]> result = qnaRepository.searchPage("t", "title", pageable);
    }

    @Test
    public void testGetQnaWithReply(){
        List<Object[]> result = qnaRepository.getQnaWithReply(20L);

        System.out.println(result);

        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }
    }


}
