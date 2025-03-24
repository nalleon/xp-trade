package es.iespuertodelacruz.xptrade.model.service.interfaces;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces")
public interface ICommentSoapService {
    @WebMethod
    Comment save(@WebParam(name = "comment") Comment comment);
    @WebMethod
    @WebResult(name="comment")
    List<Comment> findAll();
    @WebMethod
    Comment findById(@WebParam(name = "id") Integer id);
    @WebMethod
    @WebResult(name="comment")
    List<Comment> findAllByUser(@WebParam(name = "user") User user);
    @WebMethod
    @WebResult(name="comment")
    List<Comment> findAllBySubject(@WebParam(name = "post") Post post);
    @WebMethod
    boolean delete(@WebParam(name = "id") Integer id);
    @WebMethod
    Comment update(@WebParam(name = "comment") Comment comment);
}
