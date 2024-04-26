package shop.philoarte.api.art.service;

import shop.philoarte.api.art.domain.*;
import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.artist.domain.dto.ArtistDto;
import shop.philoarte.api.category.domain.Category;
import shop.philoarte.api.category.domain.CategoryDto;
import shop.philoarte.api.resume.domain.Resume;
import shop.philoarte.api.resume.domain.ResumeDto;

import java.util.List;
import java.util.stream.Collectors;

public interface ArtService {
        Long register(ArtDTO artDTO);

        PageResultDTO<ArtDTO, Object[]> getList(PageRequestDTO pageRequestDTO); // 목록 처리

        PageResultDTO<ArtDTO, Object[]> getSearch(PageRequestDTO pageRequestDTO);

        ArtDTO get(Long artId);

        List<ArtFile> getFilesByArtId(Long artId);

        Long modify(ArtDTO artDTO);

        Long delete(Long artId);

        List<Object[]> countByArtistId(Long artistId);

        PageResultDTO<ArtDTO, Object[]> getArtsByArtistId(PageRequestDTO pageRequestDTO, Long resumeId);

        default Art dtoToEntity(ArtDTO artDTO) {
                return Art.builder().title(artDTO.getTitle()).description(artDTO.getDescription())
                                .mainImg(artDTO.getMainImg())
                                .artist(Artist.builder().artistId(artDTO.getArtist().getArtistId()).build())
                                .category(Category.builder().categoryId(artDTO.getCategory().getCategoryId()).build())
                                .resume(Resume.builder().resumeId(artDTO.getResume().getResumeId()).build()).build();
        }

        default ArtDTO entityToDto(Art art, Artist artist, Category category, Resume resume,
                        List<ArtFile> artFileList) {
                return ArtDTO.builder().artId(art.getArtId()).title(art.getTitle()).description(art.getDescription())
                                .mainImg(art.getMainImg()).regDate(art.getRegDate())
                                .artist(ArtistDto.builder().artistId(artist.getArtistId())
                                                .username(artist.getUsername()).name(artist.getName()).build())
                                .category(CategoryDto.builder().categoryId(category.getCategoryId())
                                                .categoryName(category.getCategoryName()).build())
                                .resume(ResumeDto.builder().resumeId(resume.getResumeId()).build())
                                .files(artFileList.stream().map(this::entityToDtoFiles).collect(Collectors.toList()))
                                .build();
        }

        default ArtDTO entityToDtoForList(Art art, Artist artist, Category category, Resume resume, ArtFile artFile) {
                return ArtDTO.builder().artId(art.getArtId()).title(art.getTitle()).description(art.getDescription())
                                .mainImg(art.getMainImg())
                                .artist(ArtistDto.builder().artistId(artist.getArtistId())
                                                .username(artist.getUsername()).name(artist.getName()).build())
                                .category(CategoryDto.builder().categoryId(category.getCategoryId())
                                                .categoryName(category.getCategoryName()).build())
                                .resume(ResumeDto.builder().resumeId(resume.getResumeId()).build())
                                .file(ArtFileDTO.builder().fileId(artFile.getFileId()).uuid(artFile.getUuid())
                                                .originalFileName(artFile.getOriginalFileName())
                                                .savedFileName(artFile.getSavedFileName())
                                                .workedDate(artFile.getWorkedDate()).repImg(artFile.getRepImg())
                                                .art(ArtDTO.builder().artId(artFile.getArt().getArtId()).build())
                                                .build())
                                .build();
        }

        default ArtFile dtoToEntityFiles(ArtFileDTO artFileDTO) {
                return ArtFile.builder().uuid(artFileDTO.getUuid()).originalFileName(artFileDTO.getOriginalFileName())
                                .savedFileName(artFileDTO.getUuid() + "_" + artFileDTO.getOriginalFileName())
                                .workedDate(artFileDTO.getWorkedDate()).repImg(artFileDTO.getRepImg()).build();
        }

        default ArtFileDTO entityToDtoFiles(ArtFile artFile) {
                return ArtFileDTO.builder().fileId(artFile.getFileId()).uuid(artFile.getUuid())
                                .originalFileName(artFile.getOriginalFileName())
                                .savedFileName(artFile.getSavedFileName()).workedDate(artFile.getWorkedDate())
                                .repImg(artFile.getRepImg())
                                .art(ArtDTO.builder().artId(artFile.getArt().getArtId()).build()).build();
        }
}
