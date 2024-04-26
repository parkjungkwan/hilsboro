package hillsboro.philoarte.scalar.services;

import java.util.List;
import java.util.function.Function;

import hillsboro.philoarte.scalar.pagers.FundingPager;
import hillsboro.philoarte.scalar.components.PageRequestVo;
import hillsboro.philoarte.scalar.entities.Funding;
import hillsboro.philoarte.scalar.entities.FundingFile;
import hillsboro.philoarte.scalar.types.FundingDto;
import hillsboro.philoarte.scalar.types.FundingFileDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface FundingService {
    String save(FundingDto requestDto);
    List<FundingFileDto> registerFile(MultipartFile[] uploadFiles);
    String delete(FundingDto postDto);
    void deleteById(long id);

    List<Funding> getAllFundings();

    Funding dtoToEntity(FundingDto dto);

    FundingDto getFundingById(long id);

    List<FundingDto> getListByHashtag(FundingDto dto, String hashtag);

    // ============fileservice below==============
    FundingFile dtoToEntityFundingFile(FundingFileDto param) ;

    // List<FundingDto> fileBoxByFunding(Long id);


    String deleteFile(Long fundingFileId);

    // ============Pagingservice Below==============
    FundingPager<FundingDto, Funding> getList(int page);

    Pageable conditionPage(int page);

    Function<Funding, FundingDto> makeDtoPage();

    FundingPager<FundingDto, Funding> getPageById(PageRequestVo requestDto, Long id);

    FundingPager<FundingDto, Funding> getPageByArtistId(PageRequestVo requestDto, Long id);

    FundingPager<FundingDto, Funding> searchTitleAndContent(PageRequestVo requestDto, String keyword);

    FundingPager<FundingDto, Funding> getByartistName(FundingPager requestDto, String name);


}