package hillsboro.philoarte.scalar.serviceImpls;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import hillsboro.philoarte.scalar.pagers.FundingPager;
import hillsboro.philoarte.scalar.components.PageRequestVo;
import hillsboro.philoarte.scalar.entities.Artist;
import hillsboro.philoarte.scalar.entities.Funding;
import hillsboro.philoarte.scalar.entities.FundingFile;
import hillsboro.philoarte.scalar.repositories.FundingDao;
import hillsboro.philoarte.scalar.repositories.FundingFileDao;
import hillsboro.philoarte.scalar.services.FundingService;
import hillsboro.philoarte.scalar.types.FundingDto;
import hillsboro.philoarte.scalar.types.FundingFileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@Service
@Log4j2
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService {

    private final FundingDao fundingDao;
    private final FundingFileDao fundingFileDao;


    @Value("${shop.upload.path}")
    private String uploadPath;


    @Override
    @Transactional
    public String save(FundingDto requestDto) {
        Funding funding = Funding.of(requestDto);
        funding.saveRequest(requestDto);

        List<FundingFileDto> fundingFiles = requestDto.getFundingFiles();
        if (!fundingFiles.isEmpty()) {
            fundingFiles.forEach(fundingFiledtos -> {
                FundingFile f = dtoToEntityFundingFile(fundingFiledtos);
                f.confirmFunding(funding);
                fundingFileDao.save(f);
            });
        }
        return (fundingDao.save(funding) != null) ? "success" : "Fail";
    }

    @Transactional
    @Override
    public List<FundingFileDto> registerFile(MultipartFile[] uploadFiles) {

        List<FundingFileDto> resultDtoList = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {

            String ofname = uploadFile.getOriginalFilename();
            String fileName = ofname.substring(ofname.lastIndexOf("\\") + 1);

            String uuid = UUID.randomUUID().toString();

            String saveName = uploadPath + File.separator + uuid + "_" + fileName;
            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);
                String thumbnailSaveName = uploadPath + File.separator + "s_" + uuid + "_" + fileName;

                File thumbnailFile = new File(thumbnailSaveName);
                // 섬네일 생성
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);

                FundingFileDto fundingFileDto = FundingFileDto.builder()

                        .fname(fileName).uuid(uuid).build();
                resultDtoList.add(fundingFileDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (ArrayList<FundingFileDto>) resultDtoList;
    }

    @Transactional
    @Override
    public String delete(FundingDto postDto) {
        Funding funding = Funding.builder().fundingId(postDto.getFundingId()).build();
        fundingDao.delete(funding);
        frepo.deleteByFundingId(funding.getFundingId());

        return (fundingDao.findById(Funding.of(postDto).getFundingId()) == null) ? "Delete Success" : "Delete Failed";
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        fundingFileDao.deleteByFundingId(id);
        fundingDao.deleteById(id);
    }

    @Transactional
    @Override
    public List<Funding> getAllFundings() {
        return fundingDao.getAllFundings();
    }

    @Override
    public Funding dtoToEntity(FundingDto dto) {
        Funding funding = Funding.builder().fundingId(dto.getFundingId()).title(dto.getTitle())
                .content(dto.getContent()).goalPrice(dto.getGoalPrice()).hashtag(dto.getHashtag()).build();
        return funding;
    }
    @Override
    public FundingFile dtoToEntityFundingFile(FundingFileDto param) {

        FundingFile fundingFile = FundingFile.builder().fundingFileId(param.getFundingFileId()).uuid(param.getUuid())
                .fname(param.getFname()).build();
        return fundingFile;
    }

    @Override
    @Transactional
    public FundingDto getFundingById(long id) {
        Funding funding = fundingDao.findById(id).orElseThrow(IllegalArgumentException::new);

        return pageEntityToDto(funding);
    }




    @Override
    public String deleteFile(Long fundingFileId) {
        fundingFileDao.deleteById(fundingFileId);
        return (fundingFileDao.findById(fundingFileId) != null) ? "Delete Success" : "Delete Failed";
    }

    @Transactional
    @Override
    public FundingPager<FundingDto, Funding> getPageById(PageRequestVo requestVo, Long id) {
        return new FundingPager<>(
                fundingDao.getPageById(requestVo.getPageable(Sort.by("fundingId").descending()), id),
                entity -> pageEntityToDto(entity));
    }

    public FundingPager<FundingDto, Funding> getPageByArtistId(PageRequestVo requestVo, Long id) {

        return new FundingPager<>(
                fundingDao.getPagebyArtistId(requestVo.getPageable(Sort.by("fundingId").descending()), id),
                (entity -> pageEntityToDto(entity)));
    }

    @Transactional
    @Override
    public FundingPager<FundingDto, Funding> searchTitleAndContent(PageRequestVo requestDto, String keyword) {

        return new FundingPager<>(
                fundingDao.searchIndex(requestDto.getPageable(Sort.by("fundingId").descending()), keyword, keyword),
                entity -> pageEntityToDto(entity));
    }

    @Transactional
    @Override
    public FundingPager<FundingDto, Funding> getByartistName(PageRequestVo requestVo, String name) {
        return new FundingPager<>(
                fundingDao.getPageByartistName(requestVo.getPageable(Sort.by("fundingId").descending()), name),
                entity -> pageEntityToDto(entity));
    }

    @Transactional
    @Override
    public FundingPager<FundingDto, Funding> getList(int page) {
        return new FundingPageDto<>(fundingDao.getRecent(conditionPage(page)), makeDtoPage());
    }

    @Transactional
    @Override
    public List<FundingDto> getListByHashtag(FundingDto dto, String hashtag) {
        List<Funding> getHashtagList = fundingDao.searchFundingByHashtag(hashtag);
        List<FundingDto> postHashtagList = FundingDto.tlist(getHashtagList);
        return postHashtagList;
    }
    @Override Funding pagedtoToEntity(FundingDto dto) {
        Funding entity = Funding.builder().fundingId(dto.getFundingId()).title(dto.getTitle()).content(dto.getContent())
                .goalPrice(dto.getGoalPrice()).hashtag(dto.getHashtag())
                .artist(Artist.builder().artistId(dto.getFundingId()).build())
                .build();
        return entity;
    }

    @Override public FundingDto pageEntityToDto(Funding entity) {
        return FundingDto.builder().
                fundingId(entity.getFundingId()).
                title(entity.getTitle())
                .content(entity.getContent())
                .goalPrice(entity.getGoalPrice())
                .hashtag(entity.getHashtag())
                .viewCnt(entity.getViewCnt())
                .artistId(entity.getArtist().getArtistId())
                .name(entity.getArtist().getName())
                .fundingFiles(entity.getFundingFiles().stream()
                        .map(fundingFileDto -> FundingFileDto.of(fundingFileDto)).collect(Collectors.toList()))
                .build();
    }

    @Override public Pageable conditionPage(int page){
        return PageRequest.of(page <= 0 ? 1 : page - 1, 8, Sort.Direction.DESC, "fundingId");
    }

    @Override public Function<Funding, FundingDto> makeDtoPage() {
        return (en -> pageentityToDto(en));
    }

}
