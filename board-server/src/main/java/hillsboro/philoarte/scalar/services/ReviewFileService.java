package hillsboro.philoarte.scalar.services;

import hillsboro.philoarte.scalar.entities.ReviewFile;
import hillsboro.philoarte.scalar.types.ReviewFileDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

public interface ReviewFileService {
    ArrayList<ReviewFileDto> saveFile(List<MultipartFile> uploadFiles);
    void reviewFileDelete(Long reviewFileId);
    ReviewFile dtoToEntity(ReviewFileDto reviewFileDto);
    ReviewFileDto entityToDto(ReviewFile reviewFile);
}
