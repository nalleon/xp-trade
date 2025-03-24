package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface IFavoriteSoapService {
    @WebMethod
    Favorite save(@WebParam(name = "favorite") Favorite favorite);
    @WebMethod
    @WebResult(name="favorite")
    List<Favorite> findAll();
    @WebMethod
    Favorite findById(@WebParam(name = "id") Integer id);
    @WebMethod
    @WebResult(name="favorite")
    List<Favorite> findAllByUser(@WebParam(name = "user") User user);
    @WebMethod
    @WebResult(name="favorite")
    List<Favorite> findAllBySubject(@WebParam(name = "game") Game game);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Favorite update(@WebParam(name = "favorite") Favorite favorite);
}
