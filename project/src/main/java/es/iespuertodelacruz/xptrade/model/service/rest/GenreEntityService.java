package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.mapper.entity.IGenreEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.GenreEntity;
import es.iespuertodelacruz.xptrade.model.repository.IGenreEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Service
public class GenreEntityService implements IGenericRepository<Genre, Integer, String> {

    /**
     * Properties
     */
    IGenreEntityRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenreEntityRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public Genre save(Genre genre) {
        if(genre == null){
            return null;
        }

        GenreEntity dbItem = repository.findByName(genre.getName()).orElse(null);

        if(dbItem != null){
            return null;
        }

        try {
            GenreEntity entity = IGenreEntityMapper.INSTANCE.toEntity(genre);
            GenreEntity savedEntity = repository.save(entity);
            return IGenreEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Genre> findAll() {
        List<GenreEntity> listEntities = repository.findAll();
        return IGenreEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Genre findById(Integer id) {
        GenreEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null){
            return IGenreEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    public Genre findByName(String name) {
        GenreEntity entityFound = repository.findByName(name).orElse(null);

        if (entityFound != null){
            return IGenreEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        int quantity = repository.deleteEntityById(id);
        return quantity > 0;
    }

    @Override
    @Transactional
    public Genre update(Genre genre) {
        if(genre == null ){
            return null;
        }

        GenreEntity dbItem = repository.findByName(genre.getName()).orElse(null);
        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setName(genre.getName());
            return IGenreEntityMapper.INSTANCE.toDomain(dbItem);
        }  catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
