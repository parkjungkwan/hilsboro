package shop.philoarte.api.art.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.philoarte.api.art.domain.ArtFileDTO;
import shop.philoarte.api.art.repository.ArtFileRepository;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class ArtFileServiceImpl implements ArtFileService {

    private final ArtFileRepository artFileRepository;

    @Value("${shop.upload.path}")
    private String uploadPath;

    @Override
    public List<ArtFileDTO> uploadFiles(List<MultipartFile> files) {

        List<ArtFileDTO> resultDtoList = new ArrayList<>();

        for (MultipartFile file : files) {
            // 실제 파일 이름에 IE나 Edge는 전체 경로가 들어오므로 \\로 구분된 마지막 글자가 파일명
            String originalFileName = file.getOriginalFilename();
            String fileName = originalFileName.substring(originalFileName.lastIndexOf("\\") + 1);

            log.info("fileName: " + fileName);

            // UUID
            String uuid = UUID.randomUUID().toString();

            // 저장할 파일 이름 중간에 "_"를 이용해서 구분하여 파일 경로 포함한 저장 파일명
            String savedFileName = uploadPath + File.separator + uuid + "_" + fileName;
            Path savePath = Paths.get(savedFileName);

            try {
                // 원본 파일 저장
                file.transferTo(savePath);

                Thumbnails.of(new File(savedFileName))
                        .size(800, 800)
                        .toFile(uploadPath + File.separator + "s_" + uuid + "_" + fileName);

                Thumbnails.of(new File(savedFileName))
                        .scale(1)
                        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(uploadPath + File.separator + "watermark.png")), 0.5f)
                        .toFile(uploadPath + File.separator + "w_" + uuid + "_" + fileName);

                ArtFileDTO artFileDTO = ArtFileDTO.builder()
                        .uuid(uuid)
                        .originalFileName(fileName)
                        .build();

                resultDtoList.add(artFileDTO);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } // end for

        return resultDtoList;
    }

    @Override
    public List<ArtFileDTO> updateFiles(List<MultipartFile> files) {
        return null;
    }

    @Override
    public Long deleteFiles(ArtFileDTO artFileDTO) {
//        File file = new File(
//                uploadPath
//                        + File.separator
//                        + artFileRepository.findByUuid(artFileDTO.getUuid()).getSaveFileName()
//        );
//
//        File thumbnail = new File(
//                uploadPath
//                        + File.separator
//                        + "s_" + artFileRepository.findById(fileId).get().getSaveFileName()
//        );
//
//        if (file.exists()) {
//            file.delete();
//            thumbnail.delete();
//        }
//
//        artFileRepository.deleteByFileId(fileId);
//
//        return (artFileRepository.findById(fileId) == null) ? 1L : 0L;
        return null;
    }
}
