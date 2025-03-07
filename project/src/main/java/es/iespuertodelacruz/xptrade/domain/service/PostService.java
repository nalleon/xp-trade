package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class PostService implements IPostService {
    /**
     * Properties
     */
    IGenericSocialRepository<Post, Integer, User, Game> repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenericSocialRepository<Post, Integer, User, Game> repository) {
        this.repository = repository;
    }


    @Override
    public Post add(Game game, User user, String content, String picture) {
        Post aux = new Post(game, user, content, picture);
        return repository.save(aux);
    }

    @Override
    public Post findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Post> findByUser(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public List<Post> findByGame(Game game) {
        return repository.findAllBySubject(game);
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Post update(int id, Game game, User user, String content, String picture) {

        Post aux = new Post(game, user, content, picture);
        aux.setId(id);

        return repository.update(aux);
    }
}
