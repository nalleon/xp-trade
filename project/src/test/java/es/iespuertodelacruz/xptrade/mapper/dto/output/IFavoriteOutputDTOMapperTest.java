package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.dto.output.FavoriteOutputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IFavoriteOutputDTOMapperTest extends MapperDTOHelper {
    Favorite domainMapper;
    FavoriteOutputDTO dtoMapper;
    List<FavoriteOutputDTO> dtoListMapper;
    List<Favorite> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IFavoriteOutputDTOMapper.INSTANCE.toDTOList(favoriteDomains);
        Assertions.assertEquals(favoriteOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        favoriteDomains = null;
        dtoListMapper = IFavoriteOutputDTOMapper.INSTANCE.toDTOList(favoriteDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IFavoriteOutputDTOMapper.INSTANCE.toDomainList(favoriteOutputDTOList);
        Assertions.assertEquals(favoriteDomains, domainListMapper, MESSAGE_ERROR);

        favoriteOutputDTOList = null;
        domainListMapper = IFavoriteOutputDTOMapper.INSTANCE.toDomainList(favoriteOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IFavoriteOutputDTOMapper.INSTANCE.toDTO(favoriteDomain);

        Assertions.assertEquals(favoriteOutputDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteOutputDTO.user(), dtoMapper.user(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteOutputDTO.game(), dtoMapper.game(), MESSAGE_ERROR);

        dtoMapper = IFavoriteOutputDTOMapper.INSTANCE.toDTO(favoriteDomain);

        favoriteDomain = null;
        dtoMapper = IFavoriteOutputDTOMapper.INSTANCE.toDTO(favoriteDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IFavoriteOutputDTOMapper.INSTANCE.toDomain(favoriteOutputDTO);

        Assertions.assertEquals(favoriteDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteDomain.getGame(), domainMapper.getGame(), MESSAGE_ERROR);

        favoriteOutputDTO = null;
        domainMapper = IFavoriteOutputDTOMapper.INSTANCE.toDomain(favoriteOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
