package es.iespuertodelacruz.xptrade.domain.interfaces.repository;

import es.iespuertodelacruz.xptrade.domain.Feedback;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public interface IFeedbackRepository {
    Feedback save(Feedback t);
    List<Feedback> findAll();
    Feedback findById(String id);
    boolean delete(String id);
    Feedback update(Feedback t);
}
