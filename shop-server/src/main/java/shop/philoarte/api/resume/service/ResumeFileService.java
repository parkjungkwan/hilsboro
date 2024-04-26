package shop.philoarte.api.resume.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import shop.philoarte.api.resume.domain.ResumeFile;
import shop.philoarte.api.resume.domain.ResumeFileDto;

public interface ResumeFileService {

    List<ResumeFileDto> uploadFile(List<MultipartFile> uploadFiles);

    void removeFiles(Long resumeId);
}
