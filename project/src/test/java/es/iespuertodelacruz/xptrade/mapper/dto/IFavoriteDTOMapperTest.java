package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.dto.FavoriteDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IFavoriteDTOMapperTest extends MapperDTOHelper {
    Favorite domainMapper;
    FavoriteDTO dtoMapper;
    List<FavoriteDTO> dtoListMapper;
    List<Favorite> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IFavoriteDTOMapper.INSTANCE.toDTOList(favoriteDomains);
        Assertions.assertEquals(favoriteDTOList, dtoListMapper, MESSAGE_ERROR);

        favoriteDomains = null;
        dtoListMapper = IFavoriteDTOMapper.INSTANCE.toDTOList(favoriteDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IFavoriteDTOMapper.INSTANCE.toDomainList(favoriteDTOList);
        Assertions.assertEquals(favoriteDomains, domainListMapper, MESSAGE_ERROR);

        favoriteDTOList = null;
        domainListMapper = IFavoriteDTOMapper.INSTANCE.toDomainList(favoriteDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IFavoriteDTOMapper.INSTANCE.toDTO(favoriteDomain);

        Assertions.assertEquals(favoriteDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteDTO.user(), dtoMapper.user(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteDTO.game(), dtoMapper.game(), MESSAGE_ERROR);

        dtoMapper = IFavoriteDTOMapper.INSTANCE.toDTO(favoriteDomain);

        favoriteDomain = null;
        dtoMapper = IFavoriteDTOMapper.INSTANCE.toDTO(favoriteDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IFavoriteDTOMapper.INSTANCE.toDomain(favoriteDTO);

        Assertions.assertEquals(favoriteDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteDomain.getGame(), domainMapper.getGame(), MESSAGE_ERROR);

        favoriteDTO = null;
        domainMapper = IFavoriteDTOMapper.INSTANCE.toDomain(favoriteDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
