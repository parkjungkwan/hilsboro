package hillsboro.philoarte.scalar.services;

import hillsboro.philoarte.scalar.entities.Art;
import hillsboro.philoarte.scalar.entities.ArtFile;
import hillsboro.philoarte.scalar.types.ArtFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArtFileService {
    List<ArtFileDto> uploadFiles(List<MultipartFile> files);
    List<ArtFileDto> updateFiles(List<MultipartFile> files);
    Long deleteFiles(ArtFileDto artFile);

    default ArtFile dtoToEntity(ArtFileDto artFile) {
        return ArtFile.builder()
                .uuid(artFile.getUuid())
                .originalFileName(artFile.getOriginalFileName())
                .savedFileName(artFile.getSavedFileName())
                .art(Art.builder().artId(artFile.getArt().getArtId()).build())
                .build();
    }
}
