package shop.philoarte.api.artist.service.fileService;

import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.artist.domain.ArtistFile;
import shop.philoarte.api.artist.domain.QArtist;
import shop.philoarte.api.artist.domain.dto.ArtistDto;
import shop.philoarte.api.artist.domain.dto.ArtistFileDto;
import shop.philoarte.api.artist.repository.ArtistRepository;
import shop.philoarte.api.artist.repository.fileRepository.ArtistFileRepository;
import shop.philoarte.api.artist.domain.pageDomainDto.PageRequestDto;
import shop.philoarte.api.artist.domain.pageDomainDto.PageResultDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Transactional
@Log4j2
@Service
@RequiredArgsConstructor
public class ArtistFileServiceImpl implements ArtistFilerService {

    private final ArtistFileRepository aritstFileRepository;
    private final ArtistRepository artistRepository;

    @Value("${shop.upload.path}")
    private String uploadPath;

    @Transactional
    @Override
    public ArrayList<ArtistFileDto> saveFile(List<MultipartFile> uploadFiles) {
        ArrayList<ArtistFileDto> ArtistFileResultList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            String ofName = uploadFile.getOriginalFilename();
            int index = ofName.lastIndexOf(".");
            String ofHeader = ofName.substring(0, index);
            String ext = ofName.substring(index);
            String uuid = UUID.randomUUID().toString();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(uploadPath).append(File.separator).append(uuid).append("_").append(ofHeader)
                    .append(ext);
            String saveName = stringBuilder.toString();
            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);
                String thumbnailSaveName = uploadPath + File.separator + "s_" + uuid + ofName;
                Thumbnails.of(new File(saveName)).size(100, 100).outputFormat("jpg").toFile(thumbnailSaveName);

                Thumbnails.of(new File(saveName)).scale(1)
                        .watermark(Positions.BOTTOM_CENTER,
                                ImageIO.read(new File(uploadPath + File.separator + "warterMark.png")), 0.5f)
                        .toFile(new File(uploadPath + File.separator + "w+" + uuid + "_" + ofName));

                ArtistFileDto artistFileDto = ArtistFileDto.builder().uuid(uuid).imgName(ofName).build();

                ArtistFileResultList.add(artistFileDto);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return ArtistFileResultList;

    }

    @Override
    public void artistFileDelete(Long artistFileId) {
        aritstFileRepository.artistFileDelete(artistFileId);
    }

    private BooleanBuilder getSearch(PageRequestDto requestDto) {
        String type = requestDto.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QArtist qArtist = QArtist.artist;
        String keyword = requestDto.getKeyword();
        BooleanExpression expression = qArtist.artistId.gt(0L); // artist > 0 조건만 생성
        booleanBuilder.and(expression);

        if (type == null || type.trim().length() == 0) { // 검색 조건이 없는 경우
            return booleanBuilder;
        }

        // 검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("u")) {
            conditionBuilder.or(qArtist.username.contains(keyword));
        }
        if (type.contains("n")) {
            conditionBuilder.or(qArtist.name.contains(keyword));
        }
        if (type.contains("e")) {
            conditionBuilder.or(qArtist.email.contains(keyword));
        }
        if (type.contains("s")) {
            conditionBuilder.or(qArtist.school.contains(keyword));
        }
        if (type.contains("d")) {
            conditionBuilder.or(qArtist.department.contains(keyword));
        }

        // 모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

    @Override
    public PageResultDto<ArtistDto, Artist> getPageFileList(PageRequestDto requestDto) { // PageRequestFileDto로하면 안됨?
                                                                                         // 왜지?
        Pageable pageable = requestDto.getPageable(Sort.by("artistId").descending());

        requestDto.setPageFileDto(aritstFileRepository.findAll());

        BooleanBuilder booleanBuilder = getSearch(requestDto); // 검색 조건 처리
        Page<Artist> result = artistRepository.findAll(booleanBuilder, pageable); // Querydsl 사용
        Function<Artist, ArtistDto> fn = (entity -> entityDto(entity));

        return new PageResultDto<>(result, fn);
    }

}
