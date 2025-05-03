package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.dto.input.FavoriteInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IFavoriteInputDTOMapperTest extends MapperInputDTOHelper {
    Favorite domainMapper;
    FavoriteInputDTO dtoMapper;
    List<FavoriteInputDTO> dtoListMapper;
    List<Favorite> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IFavoriteInputDTOMapper.INSTANCE.toDTOList(favoriteDomains);
        Assertions.assertNotNull(dtoListMapper, MESSAGE_ERROR);
        Assertions.assertEquals(favoriteInputDTOList.get(0).game().title(), dtoListMapper.get(0).game().title(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteInputDTOList.get(0).user().username(), dtoListMapper.get(0).user().username(), MESSAGE_ERROR);

        favoriteDomains = null;
        dtoListMapper = IFavoriteInputDTOMapper.INSTANCE.toDTOList(favoriteDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IFavoriteInputDTOMapper.INSTANCE.toDomainList(favoriteInputDTOList);
        Assertions.assertEquals(favoriteDomains, domainListMapper, MESSAGE_ERROR);

        favoriteInputDTOList = null;
        domainListMapper = IFavoriteInputDTOMapper.INSTANCE.toDomainList(favoriteInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IFavoriteInputDTOMapper.INSTANCE.toDTO(favoriteDomain);

        Assertions.assertEquals(favoriteInputDTO.user().username(), dtoMapper.user().username(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteInputDTO.game().title(), dtoMapper.game().title(), MESSAGE_ERROR);

        dtoMapper = IFavoriteInputDTOMapper.INSTANCE.toDTO(favoriteDomain);

        favoriteDomain = null;
        dtoMapper = IFavoriteInputDTOMapper.INSTANCE.toDTO(favoriteDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IFavoriteInputDTOMapper.INSTANCE.toDomain(favoriteInputDTO);

        Assertions.assertEquals(favoriteDomain.getUser().getUsername(), domainMapper.getUser().getUsername(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteDomain.getGame().getTitle(), domainMapper.getGame().getTitle(), MESSAGE_ERROR);

        favoriteInputDTO = null;
        domainMapper = IFavoriteInputDTOMapper.INSTANCE.toDomain(favoriteInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
