package hillsboro.philoarte.scalar.serviceImpls;

import hillsboro.philoarte.scalar.components.PageRequestVo;
import hillsboro.philoarte.scalar.components.PageResultVo;
import hillsboro.philoarte.scalar.entities.*;
import hillsboro.philoarte.scalar.repositories.ArtDao;
import hillsboro.philoarte.scalar.repositories.ArtFileDao;
import hillsboro.philoarte.scalar.services.ArtService;
import hillsboro.philoarte.scalar.types.ArtDto;
import hillsboro.philoarte.scalar.types.ArtFileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Log4j2
@RequiredArgsConstructor
@Service
public class ArtServiceImpl implements ArtService {

    private final ArtFileServiceImpl artFileService;

    private final ArtDao artDao;

    private final ArtFileDao artFileDao;

    @Transactional
    @Override
    public Long register(ArtDto param) {

        Art art = dtoToEntity(param);

        artDao.save(art);

        log.info(art);

        List<ArtFileDto> artFileDtos = param.getFiles();

        if (artFileDtos != null && artFileDtos.size() > 0) {
            artFileDtos.forEach(artFileDto -> {
                ArtFile artFile = dtoToEntityFiles(artFileDto);
                artFile.setArt(art);
                artFileDao.save(artFile);
            });
        }

        return art.getArtId();

    }

    @Transactional
    @Override
    public PageResultVo<ArtDto, Object[]> getList(PageRequestVo pageRequest) {

        log.info(pageRequest);

        Function<Object[], ArtDto> fn = (entity ->
                entityToDtoForList((Art) entity[0], (Artist) entity[1], (Category) entity[2], (Resume) entity[3], (ArtFile) entity[4]));

        Page<Object[]> result = artDao.getArts(pageRequest.getPageable(Sort.by("artId").descending()));

        return new PageResultVo<>(result, fn);

    }

    @Transactional
    @Override
    public PageResultVo<ArtDto, Object[]> getSearch(PageRequestVo vo) {

        Function<Object[], ArtDto> fn = (entity ->
                entityToDtoForList((Art) entity[0], (Artist) entity[1], (Category) entity[2], (Resume) entity[3], (ArtFile) entity[4]));

        Page<Object[]> result = artDao.searchPage(
                vo.getType(),
                vo.getKeyword(),
                vo.getPageable(Sort.by("artId").descending())
        );

        return new PageResultVo<>(result, fn);

    }

    @Transactional
    @Override
    public ArtDto get(Long artId) {

        log.info("get() : " + artId);
        List<Object[]> result = artDao.getArtByArtId(artId);

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

        return artFileDao.getFilesByArtId(artId);

    }

    @Transactional
    @Override
    public Long modify(ArtDto artDto) {

        Art art = artDao.getOne(artDto.getArtId());

        art.changeTitle(artDto.getTitle());
        art.changeDescription(artDto.getDescription());

        artDao.save(art);

        List<ArtFileDto> artFileDtos = artDto.getFiles();

        if (artFileDtos != null && artFileDtos.size() > 0) {
            artFileDtos.forEach(artFileDTO -> {
                Long fileId = artFileDao.findByUuid(artFileDTO.getUuid());

                ArtFile artFile = dtoToEntityFiles(artFileDTO);

                if (fileId != null) { // 존재하는 파일이면
                    artFile.setFileId(fileId);
                }

                artFile.setArt(art);

                artFileDao.save(artFile);
            });
        }

        return art.getArtId();

    }

    @Transactional
    @Override
    public Long delete(Long artId) {

        // 파일 부터 삭제
        artFileDao.deleteByArtId(artId);

        artDao.deleteById(artId);

        return artDao.findById(artId).isPresent() ? 0L : 1L;

    }

    @Override
    public List<Object[]> countByArtistId(Long artistId) {
        return artDao.countByArtistId(artistId);
    }

    @Transactional
    @Override
    public PageResultVo<ArtDto, Object[]> getArtsByArtistId(PageRequestVo pageRequestDTO, Long artistId) {

        Function<Object[], ArtDto> fn = (entity ->
                entityToDtoForList((Art) entity[0], (Artist) entity[1], (Category) entity[2], (Resume) entity[3], (ArtFile) entity[4]));

        Page<Object[]> result = artDao.getArtsByArtistId(pageRequestDTO.getPageable(Sort.by("artId").descending()), artistId);

        return new PageResultVo<>(result, fn);

    }

}
