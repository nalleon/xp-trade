package es.iespuertodelacruz.xptrade.model.service.interfaces;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface IUserSoapService {
    @WebMethod
    User save(@WebParam(name = "user")User user);
    @WebMethod
    List<User> findAll();
    @WebMethod
    User findById(@WebParam(name = "id")Integer id);
    @WebMethod
    User findByUserame(@WebParam(name = "username") String username);
    @WebMethod
    User findByEmail(@WebParam(name = "email") String email);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    User update(@WebParam(name = "user")User user);
    @WebMethod
    User updatePicture(@WebParam(name = "user") User user);
}
