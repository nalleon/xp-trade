package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.*;

import java.util.List;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(
        name = "IGameSoapService",
        targetNamespace = "http://interfaces.service.model.xptrade.iespuertodelacruz.es")
public interface IGameSoapService {
    @WebMethod
    Game save(@WebParam(name = "game") Game game);
    @WebMethod
    List<Game> findAll();
    @WebMethod
    List<Game> findAllByPlatform(@WebParam(name = "platform") Platform platform);
    @WebMethod
    List<Game> findAllByDeveloper(@WebParam(name = "developer") Developer developer);
    @WebMethod
    List<Game> findAllByPublisher(@WebParam(name = "publisher") Publisher publisher);
    @WebMethod
    List<Game> findAllByGenre(@WebParam(name = "genre") Genre genre);
    @WebMethod
    List<Game> findAllByRegion(@WebParam(name = "region") Region region);
    @WebMethod
    Game findById(@WebParam(name = "id") Integer id);
    @WebMethod
    Game findByTitle(@WebParam(name = "title") String title);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Game update(@WebParam(name = "game") Game game);
    @WebMethod
    Game updateCoverArt(@WebParam(name = "game") Game game);
}
