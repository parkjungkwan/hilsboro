package hillsboro.philoarte.scalar.services;


import hillsboro.philoarte.scalar.components.PageRequestVo;
import hillsboro.philoarte.scalar.components.PageResultVo;
import hillsboro.philoarte.scalar.entities.*;
import hillsboro.philoarte.scalar.types.*;

import java.util.List;
import java.util.stream.Collectors;

public interface ArtService {
        Long register(ArtDto artDTO);

        PageResultVo<ArtDto, Object[]> getList(PageRequestVo pageRequest); // 목록 처리

        PageResultVo<ArtDto, Object[]> getSearch(PageRequestVo pageRequest);

        ArtDto get(Long artId);

        List<ArtFile> getFilesByArtId(Long artId);

        Long modify(ArtDto artDTO);

        Long delete(Long artId);

        List<Object[]> countByArtistId(Long artistId);

        PageResultVo<ArtDto, Object[]> getArtsByArtistId(PageRequestVo pageRequestDTO, Long resumeId);

        default Art dtoToEntity(ArtDto artDTO) {
                return Art.builder().title(artDTO.getTitle()).description(artDTO.getDescription())
                                .mainImg(artDTO.getMainImg())
                                .artist(Artist.builder().artistId(artDTO.getArtist().getArtistId()).build())
                                .category(Category.builder().categoryId(artDTO.getCategory().getCategoryId()).build())
                                .resume(Resume.builder().resumeId(artDTO.getResume().getResumeId()).build()).build();
        }

        default ArtDto entityToDto(Art art, Artist artist, Category category, Resume resume,
                        List<ArtFile> artFileList) {
                return ArtDto.builder().artId(art.getArtId()).title(art.getTitle()).description(art.getDescription())
                                .mainImg(art.getMainImg()).regDate(art.getRegDate())
                                .artist(ArtistDto.builder().artistId(artist.getArtistId())
                                                .username(artist.getUsername()).name(artist.getName()).build())
                                .category(CategoryDto.builder().categoryId(category.getCategoryId())
                                                .categoryName(category.getCategoryName()).build())
                                .resume(ResumeDto.builder().resumeId(resume.getResumeId()).build())
                                .files(artFileList.stream().map(this::entityToDtoFiles).collect(Collectors.toList()))
                                .build();
        }

        default ArtDto entityToDtoForList(Art art, Artist artist, Category category, Resume resume, ArtFile artFile) {
                return ArtDto.builder().artId(art.getArtId()).title(art.getTitle()).description(art.getDescription())
                                .mainImg(art.getMainImg())
                                .artist(ArtistDto.builder().artistId(artist.getArtistId())
                                                .username(artist.getUsername()).name(artist.getName()).build())
                                .category(CategoryDto.builder().categoryId(category.getCategoryId())
                                                .categoryName(category.getCategoryName()).build())
                                .resume(ResumeDto.builder().resumeId(resume.getResumeId()).build())
                                .file(ArtFileDto.builder().fileId(artFile.getFileId()).uuid(artFile.getUuid())
                                                .originalFileName(artFile.getOriginalFileName())
                                                .savedFileName(artFile.getSavedFileName())
                                                .workedDate(artFile.getWorkedDate()).repImg(artFile.getRepImg())
                                                .art(ArtDto.builder().artId(artFile.getArt().getArtId()).build())
                                                .build())
                                .build();
        }

        default ArtFile dtoToEntityFiles(ArtFileDto artFile) {
                return ArtFile.builder().uuid(artFile.getUuid()).originalFileName(artFile.getOriginalFileName())
                                .savedFileName(artFile.getUuid() + "_" + artFile.getOriginalFileName())
                                .workedDate(artFile.getWorkedDate()).repImg(artFile.getRepImg()).build();
        }

        default ArtFileDto entityToDtoFiles(ArtFile artFile) {
                return ArtFileDto.builder().fileId(artFile.getFileId()).uuid(artFile.getUuid())
                                .originalFileName(artFile.getOriginalFileName())
                                .savedFileName(artFile.getSavedFileName()).workedDate(artFile.getWorkedDate())
                                .repImg(artFile.getRepImg())
                                .art(ArtDto.builder().artId(artFile.getArt().getArtId()).build()).build();
        }
}
