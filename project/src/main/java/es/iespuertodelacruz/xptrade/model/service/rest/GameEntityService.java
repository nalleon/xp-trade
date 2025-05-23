package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGameRepository;
import es.iespuertodelacruz.xptrade.mapper.entity.*;
import es.iespuertodelacruz.xptrade.model.entities.*;
import es.iespuertodelacruz.xptrade.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class GameEntityService implements IGameRepository {

    public static final String MISSING_VALUE = "TBA";
    /**
     * Properties
     */
    IGameEntityRepository repository;
    IPublisherEntityRepository publisherRepository;
    IDeveloperEntityRepository developerRepository;
    IGenreEntityRepository genreRepository;
    IRegionEntityRepository regionRepository;
    IPlatformEntityRepository platformRepository;
    ITagEntityRepository tagRepository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGameEntityRepository repository) {
        this.repository = repository;
    }


    /**
     * Setter for the autowired service
     * @param publisherRepository of the service
     */
    @Autowired
    public void setPublisherRepository(IPublisherEntityRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }


    /**
     * Setter for the autowired service
     * @param developerRepository of the service
     */
    @Autowired
    public void setDeveloperRepository(IDeveloperEntityRepository developerRepository) {
        this.developerRepository = developerRepository;
    }


    /**
     * Setter for the autowired service
     * @param genreRepository of the service
     */
    @Autowired
    public void setGenreRepository(IGenreEntityRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Setter for the autowired service
     * @param regionRepository of the service
     */
    @Autowired
    public void setRegionRepository(IRegionEntityRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    /**
     * Setter for the autowired service
     * @param platformRepository of the service
     */
    @Autowired
    public void setPlatformRepository(IPlatformEntityRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    /**
     * Setter for the autowired service
     * @param tagRepository of the service
     */
    @Autowired
    public void setTagRepository(ITagEntityRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public Game save(Game game) {
        if(game == null){
            return null;
        }

        GameEntity dbItem = repository.findByTitle(game.getTitle()).orElse(null);

        if(dbItem != null){
                return IGameEntityMapper.INSTANCE.toDomain(dbItem);
        }


        Set<DeveloperEntity> developers = checkIfItemsExist(
                game.getDeveloperSet(),
                Developer::getName,
                name -> developerRepository.findByName(name),
                DeveloperEntity::new,
                developerRepository::save
        );

        Set<GenreEntity> genres = checkIfItemsExist(
                game.getGenreSet(),
                Genre::getName,
                name -> genreRepository.findByName(name),
                GenreEntity::new,
                genreRepository::save
        );

        Set<PlatformEntity> platforms = checkIfItemsExist(
                game.getPlatformSet(),
                Platform::getName,
                name -> platformRepository.findByName(name),
                PlatformEntity::new,
                platformRepository::save
        );

        Set<PublisherEntity> publishers = checkIfItemsExist(
                game.getPublisherSet(),
                Publisher::getName,
                name -> publisherRepository.findByName(name),
                PublisherEntity::new,
                publisherRepository::save
        );

        Set<TagEntity> tags = checkIfItemsExist(
                game.getTagSet(),
                Tag::getName,
                name -> tagRepository.findByName(name),
                TagEntity::new,
                tagRepository::save
        );

        try {

            GameEntity entity = IGameEntityMapper.INSTANCE.toEntity(game);
            entity.setDeveloperEntitySet(developers);
            entity.setGenreEntitySet(genres);
            entity.setPlatformEntitySet(platforms);
            entity.setPublisherEntitySet(publishers);
            entity.setTagEntitySet(tags);

            GameEntity savedEntity = repository.save(entity);

            return IGameEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }


    public <D, E> Set<E> checkIfItemsExist(
            Set<D> domains,
            Function<D, String> getNameFunc,
            Function<String, Optional<E>> findByNameFunc,
            Function<String, E> createEntityFunc,
            Function<E, E> saveFunc) {

        Set<E> results = new HashSet<>();

        if (domains == null || domains.isEmpty()) {
            E tba = findByNameFunc.apply(MISSING_VALUE).orElse(null);

            if (tba == null) {
                tba = createEntityFunc.apply(MISSING_VALUE);
                tba = saveFunc.apply(tba);
            }

            results.add(tba);
            return results;
        }

        for (D domain : domains) {
            String name = getNameFunc.apply(domain);
            E entity = findByNameFunc.apply(name).orElse(null);

            if (entity == null) {
                entity = createEntityFunc.apply(name);
                entity = saveFunc.apply(entity);
            }

            results.add(entity);
        }

        return results;
    }


    @Override
    public List<Game> findAll() {
        List<GameEntity> listEntities = repository.findAll();
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Game> findAllByPlatform(Platform platform) {
        if(platform == null){
            return null;
        }
        List<GameEntity> listEntities = repository.findAllByPlatform(platform.getId());
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Game> findAllByDeveloper(Developer developer) {
        if(developer == null){
            return null;
        }
        List<GameEntity> listEntities = repository.findAllByDeveloper(developer.getId());
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Game> findAllByPublisher(Publisher publisher) {
        if(publisher == null){
            return null;
        }
        List<GameEntity> listEntities = repository.findAllByPublisher(publisher.getId());
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Game> findAllByGenre(Genre genre) {
        if(genre == null){
            return null;
        }
        List<GameEntity> listEntities = repository.findAllByGenre(genre.getId());
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Game> findAllByRegion(Region region) {
        if(region == null){
            return null;
        }
        List<GameEntity> listEntities = repository.findAllByRegion(region.getId());
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Game findById(Integer id) {
        GameEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null) {
            return IGameEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return null;
    }

    @Override
    public Game findByTitle(String title) {
        GameEntity entityFound = repository.findByTitle(title).orElse(null);

        if (entityFound != null) {
            return IGameEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        try {
            repository.deleteGameDeveloperRelation(id);
            repository.deleteGamePlatformRelation(id);
            repository.deleteGamePublisherRelation(id);
            repository.deleteGameRegionRelation(id);
            repository.deleteGameGenreRelation(id);
            repository.deleteGameTagRelation(id);
            repository.deleteFromPosts(id);
            int quantity = repository.deleteEntityById(id);
            return quantity > 0;
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    @Transactional
    public Game update(Game game) {
        if(game == null){
            return null;
        }

        GameEntity dbItem = repository.findById(game.getId()).orElse(null);
        if(dbItem == null){
            return null;
        }


        try {

            Set<DeveloperEntity> developers = checkIfItemsExist(
                    game.getDeveloperSet(),
                    Developer::getName,
                    name -> developerRepository.findByName(name),
                    DeveloperEntity::new,
                    developerRepository::save
            );

            Set<GenreEntity> genres = checkIfItemsExist(
                    game.getGenreSet(),
                    Genre::getName,
                    name -> genreRepository.findByName(name),
                    GenreEntity::new,
                    genreRepository::save
            );

            Set<PlatformEntity> platforms = checkIfItemsExist(
                    game.getPlatformSet(),
                    Platform::getName,
                    name -> platformRepository.findByName(name),
                    PlatformEntity::new,
                    platformRepository::save
            );

            Set<PublisherEntity> publishers = checkIfItemsExist(
                    game.getPublisherSet(),
                    Publisher::getName,
                    name -> publisherRepository.findByName(name),
                    PublisherEntity::new,
                    publisherRepository::save
            );

            Set<TagEntity> tags = checkIfItemsExist(
                    game.getTagSet(),
                    Tag::getName,
                    name -> tagRepository.findByName(name),
                    TagEntity::new,
                    tagRepository::save
            );


            GameEntity entity = IGameEntityMapper.INSTANCE.toEntity(game);
            dbItem.setTitle(entity.getTitle());
            dbItem.setCoverArt(entity.getCoverArt());
            dbItem.setSlug(entity.getSlug());
            dbItem.setRating(entity.getRating());
            dbItem.setReleased(entity.getReleased());
            dbItem.setTagEntitySet(tags);
            dbItem.setDeveloperEntitySet(developers);
            dbItem.setGenreEntitySet(genres);
            dbItem.setPlatformEntitySet(platforms);
            dbItem.setPublisherEntitySet(publishers);

            return IGameEntityMapper.INSTANCE.toDomain(dbItem);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }

    }

    @Override
    @Transactional
    public Game updateCoverArt(Game game) {
        if(game == null){
            return null;
        }

        GameEntity dbItem = repository.findById(game.getId()).orElse(null);
        if(dbItem == null){
            return null;
        }

        try {
            dbItem.setCoverArt(game.getCoverArt());
            return IGameEntityMapper.INSTANCE.toDomain(dbItem);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
