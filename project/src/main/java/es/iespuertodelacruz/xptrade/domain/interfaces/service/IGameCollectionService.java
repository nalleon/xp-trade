package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import es.iespuertodelacruz.xptrade.domain.*;

import java.util.List;

public interface IGameCollectionService {
    GameCollection add(Game game, Collection collection, Region region, Platform platform);
    GameCollection findById(Integer id);
    List<GameCollection> findByCollection (Collection collection);
    List<GameCollection> findAll();
    boolean delete(Integer id);
    GameCollection update(int id, Game game, Collection collection, Region region, Platform platform);
}
