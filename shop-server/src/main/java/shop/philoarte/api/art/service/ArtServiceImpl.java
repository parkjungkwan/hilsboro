package shop.philoarte.api.art.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.philoarte.api.art.domain.*;
import shop.philoarte.api.art.repository.ArtFileRepository;
import shop.philoarte.api.art.repository.ArtRepository;
import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.category.domain.Category;
import shop.philoarte.api.resume.domain.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Log4j2
@RequiredArgsConstructor
@Service
public class ArtServiceImpl implements ArtService {

    private final ArtFileServiceImpl artFileService;

    private final ArtRepository artRepository;

    private final ArtFileRepository artFileRepository;

    @Transactional
    @Override
    public Long register(ArtDTO artDTO) {

        Art art = dtoToEntity(artDTO);

        artRepository.save(art);

        log.info(art);

        List<ArtFileDTO> artFileDtos = artDTO.getFiles();

        if (artFileDtos != null && artFileDtos.size() > 0) {
            artFileDtos.forEach(artFileDTO -> {
                ArtFile artFile = dtoToEntityFiles(artFileDTO);
                artFile.setArt(art);
                log.info("ArtFile: " + artFile);
                artFileRepository.save(artFile);
            });
        }

        return art.getArtId();

    }

    @Transactional
    @Override
    public PageResultDTO<ArtDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], ArtDTO> fn = (entity ->
                entityToDtoForList((Art) entity[0], (Artist) entity[1], (Category) entity[2], (Resume) entity[3], (ArtFile) entity[4]));

        Page<Object[]> result = artRepository.getArts(pageRequestDTO.getPageable(Sort.by("artId").descending()));

        System.out.println("result: " + result);

        return new PageResultDTO<>(result, fn);

    }

    @Transactional
    @Override
    public PageResultDTO<ArtDTO, Object[]> getSearch(PageRequestDTO pageRequestDTO) {

        Function<Object[], ArtDTO> fn = (entity ->
                entityToDtoForList((Art) entity[0], (Artist) entity[1], (Category) entity[2], (Resume) entity[3], (ArtFile) entity[4]));

        // Page<Object[]> result = artRepository.getArts(pageRequestDTO.getPageable(Sort.by("artId").descending()));

        log.info("---------------------------");
        log.info(pageRequestDTO.toString());

        Page<Object[]> result = artRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("artId").descending())
        );

        System.out.println("result: " + result);

        return new PageResultDTO<>(result, fn);

    }

    @Transactional
    @Override
    public ArtDTO get(Long artId) {

        log.info("get() : " + artId);
        List<Object[]> result = artRepository.getArtByArtId(artId);

        log.info("result : " + Arrays.toString(result.get(0)));

        List<ArtFile> artFileList = new ArrayList<>();

        result.forEach(arr -> artFileList.add((ArtFile) arr[4]));

        log.info("artFileList: " + artFileList);

        return entityToDto(
                (Art) result.get(0)[0], (Artist) result.get(0)[1],
                (Category) result.get(0)[2], (Resume) result.get(0)[3],
                artFileList);

    }

    @Override
    public List<ArtFile> getFilesByArtId(Long artId) {

        return artFileRepository.getFilesByArtId(artId);

    }

    @Transactional
    @Override
    public Long modify(ArtDTO artDTO) {

        Art art = artRepository.getOne(artDTO.getArtId());

        System.out.println(art);

        art.changeTitle(artDTO.getTitle());
        art.changeDescription(artDTO.getDescription());

        artRepository.save(art);

        List<ArtFileDTO> artFileDtos = artDTO.getFiles();

        if (artFileDtos != null && artFileDtos.size() > 0) {
            artFileDtos.forEach(artFileDTO -> {
                Long fileId = artFileRepository.findByUuid(artFileDTO.getUuid());

                ArtFile artFile = dtoToEntityFiles(artFileDTO);

                if (fileId != null) { // 존재하는 파일이면
                    artFile.setFileId(fileId);
                }

                artFile.setArt(art);

                log.info("ArtFile: " + artFile);

                artFileRepository.save(artFile);
            });
        }

        return art.getArtId();

    }

    @Transactional
    @Override
    public Long delete(Long artId) {

        // 파일 부터 삭제
        artFileRepository.deleteByArtId(artId);

        artRepository.deleteById(artId);

        return artRepository.findById(artId).isPresent() ? 0L : 1L;

    }

    @Override
    public List<Object[]> countByArtistId(Long artistId) {
        return artRepository.countByArtistId(artistId);
    }

    @Transactional
    @Override
    public PageResultDTO<ArtDTO, Object[]> getArtsByArtistId(PageRequestDTO pageRequestDTO, Long artistId) {

        log.info(pageRequestDTO);

        Function<Object[], ArtDTO> fn = (entity ->
                entityToDtoForList((Art) entity[0], (Artist) entity[1], (Category) entity[2], (Resume) entity[3], (ArtFile) entity[4]));

        Page<Object[]> result = artRepository.getArtsByArtistId(pageRequestDTO.getPageable(Sort.by("artId").descending()), artistId);

        System.out.println("result: " + result);

        return new PageResultDTO<>(result, fn);

    }

}
