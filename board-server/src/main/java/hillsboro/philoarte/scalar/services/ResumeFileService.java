package hillsboro.philoarte.scalar.services;

import java.util.List;

import hillsboro.philoarte.scalar.types.ResumeFileDto;
import org.springframework.web.multipart.MultipartFile;


public interface ResumeFileService {

    List<ResumeFileDto> uploadFile(List<MultipartFile> uploadFiles);

    void removeFiles(Long resumeId);
}
