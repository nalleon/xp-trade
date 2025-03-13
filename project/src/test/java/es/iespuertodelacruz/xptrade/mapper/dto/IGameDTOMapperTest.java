package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Game;

import es.iespuertodelacruz.xptrade.dto.GameDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IGameDTOMapperTest extends MapperDTOHelper {
    Game domainMapper;
    GameDTO dtoMapper;
    List<GameDTO> dtoListMapper;
    List<Game> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IGameDTOMapper.INSTANCE.toDTOList(gameDomains);
        Assertions.assertEquals(gameDTOList, dtoListMapper, MESSAGE_ERROR);

        gameDomains = null;
        dtoListMapper = IGameDTOMapper.INSTANCE.toDTOList(gameDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IGameDTOMapper.INSTANCE.toDomainList(gameDTOList);
        Assertions.assertEquals(gameDomains, domainListMapper, MESSAGE_ERROR);

        gameDTOList = null;
        domainListMapper = IGameDTOMapper.INSTANCE.toDomainList(gameDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IGameDTOMapper.INSTANCE.toDTO(gameDomain);

        Assertions.assertEquals(gameDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDTO.title(), dtoMapper.title(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDTO.coverArt(), dtoMapper.coverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDTO.developerDTOSet(), dtoMapper.developerDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDTO.genreDTOSet(), dtoMapper.genreDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDTO.platformDTOSet(), dtoMapper.platformDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDTO.publisherDTOSet(), dtoMapper.publisherDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDTO.regionDTOSet(), dtoMapper.regionDTOSet(), MESSAGE_ERROR);


        gameDomain.setGenreSet(null);
        gameDomain.setRegionSet(null);
        gameDomain.setPlatformSet(null);
        gameDomain.setDeveloperSet(null);
        gameDomain.setPublisherSet(null);

        dtoMapper = IGameDTOMapper.INSTANCE.toDTO(gameDomain);
        Assertions.assertNull(dtoMapper.regionDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.publisherDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.platformDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.developerDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.genreDTOSet(), MESSAGE_ERROR);

        gameDomain = null;
        dtoMapper = IGameDTOMapper.INSTANCE.toDTO(gameDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IGameDTOMapper.INSTANCE.toDomain(gameDTO);

        Assertions.assertEquals(gameDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getTitle(), domainMapper.getTitle(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getCoverArt(), domainMapper.getCoverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getDeveloperSet(), domainMapper.getDeveloperSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getGenreSet(), domainMapper.getGenreSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getPlatformSet(), domainMapper.getPlatformSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getPublisherSet(), domainMapper.getPublisherSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getRegionSet(), domainMapper.getRegionSet(), MESSAGE_ERROR);

        gameDTO = new GameDTO(ID, TITLE, COVER_ART, null, null, null,null,null);

        domainMapper = IGameDTOMapper.INSTANCE.toDomain(gameDTO);
        Assertions.assertNull(domainMapper.getRegionSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getPlatformSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getDeveloperSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getPublisherSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getGenreSet(), MESSAGE_ERROR);

        gameDTO = null;
        domainMapper = IGameDTOMapper.INSTANCE.toDomain(gameDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
