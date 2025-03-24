package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface IPublisherSoapService {
    @WebMethod
    Publisher save(@WebParam(name = "publisher") Publisher publisher);
    @WebMethod
    @WebResult(name="publisher")
    List<Publisher> findAll();
    @WebMethod
    Publisher findById(@WebParam(name = "id") Integer id);
    @WebMethod
    Publisher findByName(@WebParam(name = "username") String name);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Publisher update(@WebParam(name = "publisher") Publisher publisher);
}
