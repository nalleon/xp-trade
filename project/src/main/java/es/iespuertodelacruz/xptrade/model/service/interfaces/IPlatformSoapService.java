package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.Platform;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface IPlatformSoapService {
    @WebMethod
    Platform save(@WebParam(name = "platform") Platform platform);
    @WebMethod
    @WebResult(name="platform")
    List<Platform> findAll();
    @WebMethod
    Platform findById(@WebParam(name = "id") Integer id);
    @WebMethod
    Platform findByName(@WebParam(name = "username") String name);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Platform update(@WebParam(name = "platform") Platform platform);
}
