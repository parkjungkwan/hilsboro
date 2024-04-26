package shop.philoarte.api.artist.service.artistTestService;

import shop.philoarte.api.artist.domain.Artist;
import shop.philoarte.api.artist.domain.dto.ArtistDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructService {
    MapStructService INSTANCE = Mappers.getMapper(MapStructService.class);

    Artist dtoToArtist(ArtistDto artistDto);

    ArtistDto entityToArtist(Artist artist);
}
