package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Feedback;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IFeedbackRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public class FeedbackService implements IFeedbackService {

    /**
     * Properties
     */
    private IFeedbackRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IFeedbackRepository repository) {
        this.repository = repository;
    }

    @Override
    public Feedback add(String content) {
        Feedback aux = new Feedback();
        aux.setContent(content);
        return repository.save(aux);
    }

    @Override
    public Feedback findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Feedback> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public Feedback update(String id, String content) {
        Feedback aux = new Feedback(id, content);
        return repository.update(aux);
    }
}
