package shop.philoarte.api.art.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.philoarte.api.art.domain.ArtDTO;
import shop.philoarte.api.art.domain.PageRequestDTO;
import shop.philoarte.api.art.domain.PageResultDTO;
import shop.philoarte.api.art.service.ArtServiceImpl;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/arts")
public class ArtController {

    private final ArtServiceImpl artService;

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<ArtDTO, Object[]>> list(PageRequestDTO pageRequestDTO) {

        PageResultDTO<ArtDTO, Object[]> result = artService.getList(pageRequestDTO);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<PageResultDTO<ArtDTO, Object[]>> search(PageRequestDTO pageRequestDTO) {

        return ResponseEntity.ok(artService.getSearch(pageRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody ArtDTO artDTO) {

        return ResponseEntity.ok(artService.register(artDTO));
    }

    @GetMapping("/read/{artId}")
    public ResponseEntity<ArtDTO> read(@PathVariable("artId") Long artId) {

        return ResponseEntity.ok(artService.get(artId));
    }

    @PutMapping("/modify")
    public ResponseEntity<Long> modify(@RequestBody ArtDTO artDTO) {

        return ResponseEntity.ok(artService.modify(artDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody ArtDTO artDTO) {

        return ResponseEntity.ok(artService.delete(artDTO.getArtId()));
    }

    @GetMapping("/count/{artistId}")
    public ResponseEntity<List<Object[]>> count(@PathVariable("artistId") Long artistId) {

        return ResponseEntity.ok(artService.countByArtistId(artistId));
    }

    @GetMapping("/list/{artistId}")
    public ResponseEntity<PageResultDTO<ArtDTO, Object[]>> listByArtistId(PageRequestDTO pageRequestDTO, @PathVariable("artistId") Long artistId) {

        return ResponseEntity.ok(artService.getArtsByArtistId(pageRequestDTO, artistId));
    }

}
