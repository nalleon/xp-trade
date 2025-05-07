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
import java.util.Set;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class GameEntityService implements IGameRepository {

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
        Set<RegionEntity> regions = checkIfRegionsExists(game.getRegionSet());

        if (regions == null){
            return null;
        }

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


        Set<PlatformEntity> platforms = checkIfPlatformExists(game.getPlatformSet());
        Set<DeveloperEntity> developers = checkIfDevelopersExists(game.getDeveloperSet());
        Set<PublisherEntity> publishers = checkIfPublishersExists(game.getPublisherSet());
        Set<GenreEntity> genres = checkIfGenresExists(game.getGenreSet());

        if (platforms == null || developers == null || publishers == null || genres == null){
            return null;
        }
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

    public Set<DeveloperEntity> checkIfDevelopersExists(Set<Developer> domains){
        if(domains.isEmpty()){
            return null;
        }
    
        Set<DeveloperEntity> results = new HashSet<>();
        
        for (Developer domain : domains){
            DeveloperEntity item = developerRepository.findByName(domain.getName()).orElse(null);
            
            if(item == null){
                item = new DeveloperEntity(domain.getName());
                item = developerRepository.save(item);
            }
            
            results.add(item);
        }
        
        return results;
    }


    public Set<PublisherEntity> checkIfPublishersExists(Set<Publisher> domains){
        if(domains.isEmpty()){
            return null;
        }

        Set<PublisherEntity> results = new HashSet<>();

        for (Publisher domain : domains){
            PublisherEntity item = publisherRepository.findByName(domain.getName()).orElse(null);

            if(item == null){
                item = new PublisherEntity(domain.getName());
                item = publisherRepository.save(item);
            }

            results.add(item);
        }

        return results;
    }

    public Set<GenreEntity> checkIfGenresExists(Set<Genre> domains){
        if(domains.isEmpty()){
            return null;
        }

        Set<GenreEntity> results = new HashSet<>();

        for (Genre domain : domains){
            GenreEntity item = genreRepository.findByName(domain.getName()).orElse(null);

            if(item == null){
                item = new GenreEntity(domain.getName());
                item = genreRepository.save(item);
            }

            results.add(item);
        }

        return results;
    }

    public Set<RegionEntity> checkIfRegionsExists(Set<Region> domains){
        if(domains.isEmpty()){
            return null;
        }

        Set<RegionEntity> results = new HashSet<>();

        for (Region domain : domains){
            RegionEntity item = regionRepository.findByName(domain.getName()).orElse(null);

            if(item == null){
                item = new RegionEntity(domain.getName());
                item = regionRepository.save(item);
            }

            results.add(item);
        }

        return results;
    }

    public Set<PlatformEntity> checkIfPlatformExists(Set<Platform> domains){
        if(domains.isEmpty()){
            return null;
        }

        Set<PlatformEntity> results = new HashSet<>();

        for (Platform domain : domains){
            PlatformEntity item = platformRepository.findByName(domain.getName()).orElse(null);

            if(item == null){
                item = new PlatformEntity(domain.getName());
                item = platformRepository.save(item);
            }

            results.add(item);
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

            Set<RegionEntity> regions = checkIfRegionsExists(game.getRegionSet());
            Set<PlatformEntity> platforms = checkIfPlatformExists(game.getPlatformSet());
            Set<DeveloperEntity> developers = checkIfDevelopersExists(game.getDeveloperSet());
            Set<PublisherEntity> publishers = checkIfPublishersExists(game.getPublisherSet());
            Set<GenreEntity> genres = checkIfGenresExists(game.getGenreSet());

            if (regions == null || platforms == null || developers == null || publishers == null || genres == null){
                return null;
            }

            GameEntity entity = IGameEntityMapper.INSTANCE.toEntity(game);
            dbItem.setTitle(entity.getTitle());
            dbItem.setCoverArt(entity.getCoverArt());
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
