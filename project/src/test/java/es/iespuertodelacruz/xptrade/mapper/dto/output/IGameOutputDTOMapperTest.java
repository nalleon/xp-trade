package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Game;

import es.iespuertodelacruz.xptrade.dto.output.GameOutputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IGameOutputDTOMapperTest extends MapperDTOHelper {
    Game domainMapper;
    GameOutputDTO dtoMapper;
    List<GameOutputDTO> dtoListMapper;
    List<Game> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IGameOutputDTOMapper.INSTANCE.toDTOList(gameDomains);
        Assertions.assertEquals(gameOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        gameDomains = null;
        dtoListMapper = IGameOutputDTOMapper.INSTANCE.toDTOList(gameDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IGameOutputDTOMapper.INSTANCE.toDomainList(gameOutputDTOList);
        Assertions.assertEquals(gameDomains, domainListMapper, MESSAGE_ERROR);

        gameOutputDTOList = null;
        domainListMapper = IGameOutputDTOMapper.INSTANCE.toDomainList(gameOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IGameOutputDTOMapper.INSTANCE.toDTO(gameDomain);

        Assertions.assertEquals(gameOutputDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(gameOutputDTO.title(), dtoMapper.title(), MESSAGE_ERROR);
        Assertions.assertEquals(gameOutputDTO.coverArt(), dtoMapper.coverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(gameOutputDTO.developerOutputDTOSet(), dtoMapper.developerOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameOutputDTO.genreOutputDTOSet(), dtoMapper.genreOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameOutputDTO.platformOutputDTOSet(), dtoMapper.platformOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameOutputDTO.publisherOutputDTOSet(), dtoMapper.publisherOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameOutputDTO.regionOutputDTOSet(), dtoMapper.regionOutputDTOSet(), MESSAGE_ERROR);


        gameDomain.setGenreSet(null);
        gameDomain.setRegionSet(null);
        gameDomain.setPlatformSet(null);
        gameDomain.setDeveloperSet(null);
        gameDomain.setPublisherSet(null);

        dtoMapper = IGameOutputDTOMapper.INSTANCE.toDTO(gameDomain);
        Assertions.assertNull(dtoMapper.regionOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.publisherOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.platformOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.developerOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertNull(dtoMapper.genreOutputDTOSet(), MESSAGE_ERROR);

        gameDomain = null;
        dtoMapper = IGameOutputDTOMapper.INSTANCE.toDTO(gameDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IGameOutputDTOMapper.INSTANCE.toDomain(gameOutputDTO);

        Assertions.assertEquals(gameDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getTitle(), domainMapper.getTitle(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getCoverArt(), domainMapper.getCoverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getDeveloperSet(), domainMapper.getDeveloperSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getGenreSet(), domainMapper.getGenreSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getPlatformSet(), domainMapper.getPlatformSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getPublisherSet(), domainMapper.getPublisherSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getRegionSet(), domainMapper.getRegionSet(), MESSAGE_ERROR);

        gameOutputDTO = new GameOutputDTO(ID, TITLE, COVER_ART, null, null, null,null,null);

        domainMapper = IGameOutputDTOMapper.INSTANCE.toDomain(gameOutputDTO);
        Assertions.assertNull(domainMapper.getRegionSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getPlatformSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getDeveloperSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getPublisherSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getGenreSet(), MESSAGE_ERROR);

        gameOutputDTO = null;
        domainMapper = IGameOutputDTOMapper.INSTANCE.toDomain(gameOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
