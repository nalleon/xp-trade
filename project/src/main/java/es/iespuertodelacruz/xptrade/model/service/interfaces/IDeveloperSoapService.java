package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.Developer;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface IDeveloperSoapService {
    @WebMethod
    Developer save(@WebParam(name = "developer") Developer developer);
    @WebMethod
    @WebResult(name="developer")
    List<Developer> findAll();
    @WebMethod
    Developer findById(@WebParam(name = "id") Integer id);
    @WebMethod
    Developer findByName(@WebParam(name = "username") String name);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Developer update(@WebParam(name = "developer") Developer developer);
}
