package es.iespuertodelacruz.xptrade.model.service.interfaces;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(
        name = "IGenericSocialSoapService",
        targetNamespace = "http://interfaces.service.model.xptrade.iespuertodelacruz.es")
public interface IGenericSocialSoapService<T, E, U, G> {
    @WebMethod
    T save(@WebParam(name = "item") T t);
    @WebMethod
    List<T> findAll();
    @WebMethod
    T findById(@WebParam(name = "id") E id);
    @WebMethod
    List<T> findAllByUser(@WebParam(name = "user") U u);
    @WebMethod
    List<T> findAllBySubject(@WebParam(name = "item") G g);
    @WebMethod
    boolean delete(@WebParam(name = "id") E id);
    @WebMethod
    T update(@WebParam(name = "item") T t);
}
