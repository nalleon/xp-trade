package es.iespuertodelacruz.xptrade.model.service.interfaces;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

import es.iespuertodelacruz.xptrade.domain.User;

@WebService(
        name = "IUserSoapService",
        targetNamespace = "http://interfaces.service.model.xptrade.iespuertodelacruz.es"
)
public interface IUserSoapService {
    @WebMethod
    User save(@WebParam(name = "user")User user);
    @WebMethod
    List<User> findAll();
    @WebMethod
    User findById(@WebParam(name = "id")Integer id);
    @WebMethod
    User findByUsername(@WebParam(name = "username") String username);
    @WebMethod
    User findByEmail(@WebParam(name = "email") String email);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    User update(@WebParam(name = "user")User user);
}
