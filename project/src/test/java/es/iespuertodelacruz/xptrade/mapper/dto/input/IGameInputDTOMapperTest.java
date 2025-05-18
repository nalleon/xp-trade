package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.dto.input.GameInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;

public class IGameInputDTOMapperTest extends MapperInputDTOHelper {
    Game domainMapper;
    GameInputDTO dtoMapper;
    List<GameInputDTO> dtoListMapper;
    List<Game> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IGameInputDTOMapper.INSTANCE.toDTOList(gameDomains);
        Assertions.assertEquals(gameInputDTOList, dtoListMapper, MESSAGE_ERROR);

        gameDomains = null;
        dtoListMapper = IGameInputDTOMapper.INSTANCE.toDTOList(gameDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IGameInputDTOMapper.INSTANCE.toDomainList(gameInputDTOList);
        Assertions.assertEquals(gameDomains, domainListMapper, MESSAGE_ERROR);

        gameInputDTOList = null;
        domainListMapper = IGameInputDTOMapper.INSTANCE.toDomainList(gameInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IGameInputDTOMapper.INSTANCE.toDTO(gameDomain);

        Assertions.assertEquals(gameInputDTO.title(), dtoMapper.title(), MESSAGE_ERROR);
        Assertions.assertEquals(gameInputDTO.coverArt(), dtoMapper.coverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(gameInputDTO.slug(), dtoMapper.slug(), MESSAGE_ERROR);
        Assertions.assertEquals(gameInputDTO.released(), dtoMapper.released(), MESSAGE_ERROR);
        Assertions.assertEquals(gameInputDTO.rating(), dtoMapper.rating(), MESSAGE_ERROR);
        Assertions.assertEquals(gameInputDTO.tagInputDTOSet(), dtoMapper.tagInputDTOSet(), MESSAGE_ERROR);

        Assertions.assertEquals(gameInputDTO.developerInputDTOSet(), dtoMapper.developerInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameInputDTO.genreInputDTOSet(), dtoMapper.genreInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameInputDTO.platformInputDTOSet(), dtoMapper.platformInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameInputDTO.publisherInputDTOSet(), dtoMapper.publisherInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameInputDTO.tagInputDTOSet(), dtoMapper.tagInputDTOSet(), MESSAGE_ERROR);


        gameDomain.setGenreSet(null);
        gameDomain.setPlatformSet(null);
        gameDomain.setDeveloperSet(null);
        gameDomain.setPublisherSet(null);
        gameDomain.setTagSet(null);

        dtoMapper = IGameInputDTOMapper.INSTANCE.toDTO(gameDomain);
        Assertions.assertNull(dtoMapper.publisherInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.platformInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.developerInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.genreInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.tagInputDTOSet(), MESSAGE_ERROR);

        gameDomain = null;
        dtoMapper = IGameInputDTOMapper.INSTANCE.toDTO(gameDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IGameInputDTOMapper.INSTANCE.toDomain(gameInputDTO);

        Assertions.assertEquals(gameDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getTitle(), domainMapper.getTitle(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getCoverArt(), domainMapper.getCoverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getSlug(), domainMapper.getSlug(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getRating(), domainMapper.getRating(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getReleased(), domainMapper.getReleased(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getDeveloperSet(), domainMapper.getDeveloperSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getGenreSet(), domainMapper.getGenreSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getPlatformSet(), domainMapper.getPlatformSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getPublisherSet(), domainMapper.getPublisherSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getTagSet(), domainMapper.getTagSet(), MESSAGE_ERROR);

        gameInputDTO = new GameInputDTO(TITLE, COVER_ART, SLUG, RATING, RELEASED, null,null,  null,null,null);

        domainMapper = IGameInputDTOMapper.INSTANCE.toDomain(gameInputDTO);
        Assertions.assertNull(domainMapper.getPlatformSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getDeveloperSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getPublisherSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getGenreSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getTagSet(), MESSAGE_ERROR);

        gameInputDTO = null;
        domainMapper = IGameInputDTOMapper.INSTANCE.toDomain(gameInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
