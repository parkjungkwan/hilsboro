package shop.philoarte.api.artist.service;

import java.io.File;
import shop.philoarte.api.artist.domain.*;
import shop.philoarte.api.artist.domain.dto.ArtistDto;
import shop.philoarte.api.artist.domain.role.Role;
import shop.philoarte.api.artist.repository.fileRepository.ArtistFileRepository;
import shop.philoarte.api.artist.domain.pageDomainDto.PageRequestDto;
import shop.philoarte.api.artist.repository.ArtistRepository;
import shop.philoarte.api.artist.domain.pageDomainDto.PageResultDto;
import shop.philoarte.api.common.service.AbstractService;
import shop.philoarte.api.security.domain.SecurityProvider;
import shop.philoarte.api.security.exception.SecurityRuntimeException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@Transactional
@Log4j2
@RequiredArgsConstructor
@Service
public class ArtistServiceImpl extends AbstractService<Artist> implements ArtistService {

    private final ArtistRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityProvider provider;
    private final AuthenticationManager manager;
    private final ArtistFileRepository aritstFileRepository;

    @Value("${shop.upload.path}")
    private String uploadPath;

    @Transactional
    @Override // jpa save 사용시 insert가 아니고 update 뜨는 이유
    public Map<String, String> signup(ArtistDto artistDto) {
        if (!repository.existsByUsername(artistDto.getUsername())) {
            Map<String, Object> entityMap = dtoToEntity(artistDto);

            Artist entity = (Artist) entityMap.get("artist");

            repository.save(entity); // save 안될시 saveAndFlush 변경하자

            List<ArtistFile> artistFileList = (List<ArtistFile>) entityMap.get("fileList");

            if (artistFileList != null && artistFileList.size() > 0) {
                artistFileList.forEach(artistFile -> {
                    aritstFileRepository.save(artistFile);
                });
            }

            ArtistDto entityDto = entityDto(entity);
            entityDto.setArtistFileDtoList(artistDto.getArtistFileDtoList());
            entityDto.setPassword(passwordEncoder.encode(entityDto.getPassword()));
            List<Role> list = new ArrayList<>();
            list.add(Role.USER_ROLES);
            entity.changeRoles(list);
            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("JwtToken", provider.createToken(entityDto.getUsername(), entity.getRoles()));

            entityDto.getArtistFileDtoList().forEach(file -> {
                resultMap.put("uuid", file.getUuid());
                resultMap.put("imgName", file.getImgName());
            });

            return resultMap;

        } else {
            throw new SecurityRuntimeException("Artist Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ArtistDto signin(ArtistDto artistDto) {
        try {
            Artist entity = dtoEntity(artistDto);
            repository.signin(entity.getUsername(), entity.getPassword());

            ArtistDto entityDto = entityDto(entity);
            Optional<Artist> comprehensiveInfomationArtist = repository.findByUsername(entity.getUsername());
            Long artistFileId = comprehensiveInfomationArtist.get().getArtistId();
            entityDto(comprehensiveInfomationArtist.get());
            entityDto = entityDto(comprehensiveInfomationArtist.get());
            String Token = provider.createToken(entity.getUsername(),
                    repository.findByUsername(entity.getUsername()).get().getRoles());
            entityDto.setToken(Token);
            Long artistFileIdSetting = entityDto.getArtistId();

            Optional<ArtistFile> fileListResult = aritstFileRepository.findById(artistFileIdSetting);

            if (fileListResult.isPresent()) {
                fileListResult.get().getArtistFileId();

                String uuid = fileListResult.get().getUuid();
                String imgName = fileListResult.get().getImgName();
                entityDto.setUuid(uuid);
                entityDto.setImgName(imgName);
            } else {
                entityDto.setUuid("fd05e3c1-0eb2-4062-88be-8be96f833ab9");
                entityDto.setImgName("aaa.jpg");

            }
            return entityDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SecurityRuntimeException("Invalid Artist-Username / Password supplied",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public List<Artist> getAllData() {
        return repository.getAllData();
    }

    @Override
    public void deleteById(Long artistId) {
        repository.deleteById(artistId);
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public Optional<Artist> getOne(Long id) {
        return Optional.ofNullable(repository.getOne(id));
    }

    @Override
    public String delete(Artist artist) {
        repository.delete(artist);
        return repository.findById(artist.getArtistId()).orElse(null) == null ? "success" : "fail";
    }

    @Override
    public Optional<Artist> findById(Long artistId) {
        return repository.findById(artistId);
    }

    @Override
    public List<Artist> findAll() {
        return repository.getAllData();
    }

    @Override
    public ArtistDto updateById(ArtistDto artistDto) {
        Artist entity = dtoEntity(artistDto);

        repository.save(entity);
        ArtistDto dtoEntity = entityDto(entity);
        return dtoEntity;
    }

    @Transactional
    @Override
    public ArtistDto updateMypage(ArtistDto artistDto) {

        Artist artist = repository.getOne(artistDto.getArtistId());

        artist.changePassword(artistDto.getPassword());
        artist.changePhoneNumber(artistDto.getPhoneNumber());
        artist.changeEmail(artistDto.getEmail());
        artist.changeAddress(artistDto.getAddress());
        artist.changeSchool(artistDto.getSchool());
        artist.changeDepartment(artistDto.getDepartment());

        repository.save(artist);
        ArtistDto dtoEntity = entityDto(artist);
        return dtoEntity;
    }

    @Override
    public Long register(ArtistDto artistDto) {
        Artist entity = dtoEntity(artistDto);
        repository.save(entity);
        return null;
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
    public PageResultDto<ArtistDto, Artist> getPageList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("artistId").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDto); // 검색 조건 처리
        Page<Artist> result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용
        Function<Artist, ArtistDto> fn = (entity -> entityDto(entity));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public Boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String save(Artist t) {
        // TODO Auto-generated method stub
        return null;
    }
}
