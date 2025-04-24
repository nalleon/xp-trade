package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSoapService;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSoapService")
@Component
public class GenreSoapService implements IGenericSoapService<Genre, Integer, String> {
    /**
     * Properties
     */
    private IGenericRepository<Genre, Integer, String> service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService( IGenericRepository<Genre, Integer, String> service) {
        this.service = service;
    }

    @Override
    public Genre save(Genre genre) {
        return service.save(genre);
    }

    @Override
    public List<Genre> findAll() {
        return service.findAll();
    }

    @Override
    public Genre findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public Genre findByName(String name) {
        return service.findByName(name);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Genre update(Genre genre) {
        return service.update(genre);
    }
}
