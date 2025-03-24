package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.Genre;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface IGenreSoapService {
    @WebMethod
    Genre save(@WebParam(name = "genre") Genre genre);
    @WebMethod
    @WebResult(name="genre")
    List<Genre> findAll();
    @WebMethod
    Genre findById(@WebParam(name = "id") Integer id);
    @WebMethod
    Genre findByName(@WebParam(name = "username") String name);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Genre update(@WebParam(name = "genre") Genre genre);
}
