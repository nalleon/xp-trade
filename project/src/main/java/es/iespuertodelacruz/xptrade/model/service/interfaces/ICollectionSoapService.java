package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;
@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface ICollectionSoapService {
    @WebMethod
    Collection save(@WebParam(name = "collection") Collection collection);
    @WebMethod
    @WebResult(name="collection")
    List<Collection> findAll();
    @WebMethod
    Collection findById(@WebParam(name = "id") Integer id);
    @WebMethod
    @WebResult(name="collection")
    List<Collection> findAllByUser(@WebParam(name = "user") User user);
    @WebMethod
    @WebResult(name="collection")
    List<Collection> findAllBySubject(@WebParam(name = "game") Game game);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Collection update(@WebParam(name = "collection") Collection collection);
}
