package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.Role;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;
@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface IRoleSoapService {
    @WebMethod
    Role save(@WebParam(name = "role") Role role);
    @WebMethod
    List<Role> findAll();
    @WebMethod
    Role findById(@WebParam(name = "id") Integer id);
    @WebMethod
    Role findByName(@WebParam(name = "username") String name);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Role update(@WebParam(name = "role") Role role);
}
