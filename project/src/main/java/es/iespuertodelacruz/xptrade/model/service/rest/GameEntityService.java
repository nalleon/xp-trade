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

    @Override
    @Transactional
    public Game save(Game game) {
        if(game == null){
            return null;
        }

        Set<RegionEntity> regions = checkIfItemsExist(
                game.getRegionSet(),
                Region::getName,
                name -> regionRepository.findByName(name),
                RegionEntity::new,
                regionRepository::save
        );

        GameEntity dbItem = repository.findByTitle(game.getTitle()).orElse(null);

        if(dbItem != null){
            if(dbItem.getRegionEntitySet().equals(regions)){
                return IGameEntityMapper.INSTANCE.toDomain(dbItem);
            }

            try {
                Set<RegionEntity> updatedRegions = dbItem.getRegionEntitySet();

                for (RegionEntity region:regions){
                    if (!dbItem.getRegionEntitySet().contains(region)){
                        updatedRegions.add(region);
                    }
                }

                dbItem.setRegionEntitySet(updatedRegions);

                GameEntity updated = repository.save(dbItem);

                return IGameEntityMapper.INSTANCE.toDomain(updated);
            } catch (RuntimeException e) {
                throw new RuntimeException("Invalid region data: " + e);
            }
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


        try {

            GameEntity entity = IGameEntityMapper.INSTANCE.toEntity(game);
            entity.setDeveloperEntitySet(developers);
            entity.setGenreEntitySet(genres);
            entity.setRegionEntitySet(regions);
            entity.setPlatformEntitySet(platforms);
            entity.setPublisherEntitySet(publishers);

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

            Set<RegionEntity> regions = checkIfItemsExist(
                    game.getRegionSet(),
                    Region::getName,
                    name -> regionRepository.findByName(name),
                    RegionEntity::new,
                    regionRepository::save
            );

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


            GameEntity entity = IGameEntityMapper.INSTANCE.toEntity(game);
            dbItem.setTitle(entity.getTitle());
            dbItem.setCoverArt(entity.getCoverArt());
            dbItem.setSlug(entity.getSlug());
            dbItem.setDeveloperEntitySet(developers);
            dbItem.setGenreEntitySet(genres);
            dbItem.setPlatformEntitySet(platforms);
            dbItem.setRegionEntitySet(regions);
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
