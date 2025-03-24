package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface IPostSoapService {
    @WebMethod
    Post save(@WebParam(name = "post") Post post);
    @WebMethod
    @WebResult(name="post")
    List<Post> findAll();
    @WebMethod
    Post findById(@WebParam(name = "id") Integer id);
    @WebMethod
    @WebResult(name="post")
    List<Post> findAllByUser(@WebParam(name = "user") User user);
    @WebMethod
    @WebResult(name="post")
    List<Post> findAllBySubject(@WebParam(name = "game") Game game);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Post update(@WebParam(name = "post") Post post);
}
