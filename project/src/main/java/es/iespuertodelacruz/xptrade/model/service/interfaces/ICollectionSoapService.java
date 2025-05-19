package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface ICollectionSoapService {
    @WebMethod
    Collection save(@WebParam(name = "item") Collection collection);
    @WebMethod
    List<Collection> findAll();
    @WebMethod
    Collection findById(@WebParam(name = "id") Integer id);
    @WebMethod
    List<Collection> findAllByUser(@WebParam(name = "user") User u);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Collection update(@WebParam(name = "item") Collection t);
}

