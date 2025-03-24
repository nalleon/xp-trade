package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.Region;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface IRegionSoapService {
    @WebMethod
    Region save(@WebParam(name = "region") Region region);
    @WebMethod
    @WebResult(name="region")
    List<Region> findAll();
    @WebMethod
    Region findById(@WebParam(name = "id") Integer id);
    @WebMethod
    Region findByName(@WebParam(name = "username") String name);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Region update(@WebParam(name = "region") Region region);
}
