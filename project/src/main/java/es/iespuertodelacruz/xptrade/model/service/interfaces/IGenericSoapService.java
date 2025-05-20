package es.iespuertodelacruz.xptrade.model.service.interfaces;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(
        name = "IGenericSoapService",
        targetNamespace = "http://interfaces.service.model.xptrade.iespuertodelacruz.es")
public interface IGenericSoapService<T, E, U> {
    @WebMethod
    T save(@WebParam(name = "item")T t);
    @WebMethod
    List<T> findAll();
    @WebMethod
    T findById(@WebParam(name = "id")E id);
    @WebMethod
    T findByName(@WebParam(name = "username")U name);
    @WebMethod
    boolean delete(@WebParam(name = "id")E id);
    @WebMethod
    T update(@WebParam(name = "item")T t);
}
