package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import es.iespuertodelacruz.xptrade.domain.Feedback;

import java.util.List;

public interface IFeedbackService {
    Feedback add(String content);
    Feedback findById(String id);
    List<Feedback> findAll();
    boolean delete(String id);
    Feedback update(String id, String content);
}
