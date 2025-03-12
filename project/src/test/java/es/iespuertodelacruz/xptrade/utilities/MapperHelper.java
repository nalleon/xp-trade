package es.iespuertodelacruz.xptrade.utilities;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.model.entities.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

public class MapperHelper extends TestUtilities {


    /**
     * Properties
     */
    public RoleEntity roleEntity;
    public Role roleDomain;
    public List<RoleEntity> roleEntities;
    public List<Role> roleDomains;

    public UserEntity userEntity;
    public User userDomain;
    public List<UserEntity> userEntities;
    public List<User> userDomains;

    public GenreEntity genreEntity;
    public Genre genreDomain;
    public List<GenreEntity> genreEntities;
    public List<Genre> genreDomains;

    public PublisherEntity publisherEntity;
    public Publisher publisherDomain;
    public List<PublisherEntity> publisherEntities;
    public List<Publisher> publisherDomains;

    public DeveloperEntity developerEntity;
    public Developer developerDomain;
    public List<DeveloperEntity> developerEntities;
    public List<Developer> developerDomains;

    public PlatformEntity platformEntity;
    public Platform platformDomain;
    public List<PlatformEntity> platformEntities;
    public List<Platform> platformDomains;

    public RegionEntity regionEntity;
    public Region regionDomain;
    public List<RegionEntity> regionEntities;
    public List<Region> regionDomains;

    public GameEntity gameEntity;
    public Game gameDomain;
    public List<GameEntity> gameEntities;
    public List<Game> gameDomains;

    public CollectionEntity collectionEntity;
    public Collection collectionDomain;
    public List<CollectionEntity> collectionEntities;
    public List<Collection> collectionDomains;

    public FavoriteEntity favoriteEntity;
    public Favorite favoriteDomain;
    public List<FavoriteEntity> favoriteEntities;
    public List<Favorite> favoriteDomains;

    public PostEntity postEntity;
    public Post postDomain;
    public List<PostEntity> postEntities;
    public List<Post> postDomains;

    public CommentEntity commentEntity;
    public Comment commentDomain;
    public List<CommentEntity> commentEntities;
    public List<Comment> commentDomains;



    @BeforeEach
    public void beforeEach() {
        roleEntity = new RoleEntity();
        roleEntity.setId(ID);
        roleEntity.setName(ROLE_NAME);

        roleEntities = new ArrayList<>();
        roleEntities.add(roleEntity);

        roleDomain = new Role();
        roleDomain.setId(ID);
        roleDomain.setName(ROLE_NAME);

        roleDomains = new ArrayList<>();
        roleDomains.add(roleDomain);


        userEntity = new UserEntity();
        userEntity.setId(ID);
        userEntity.setUsername(USERNAME);
        userEntity.setEmail(EMAIL);
        userEntity.setPassword(PASSWORD);
        userEntity.setCreationDate(CREATION_DATE);
        userEntity.setVerificationToken(VERIFICATION_TOKEN);
        userEntity.setProfilePicture(PROFILE_PICTURE);
        userEntity.setRole(roleEntity);
        userEntity.setVerified(VERIFIED);

        userDomain = new User();
        userDomain.setId(ID);
        userDomain.setUsername(USERNAME);
        userDomain.setEmail(EMAIL);
        userDomain.setPassword(PASSWORD);
        userDomain.setCreationDate(CREATION_DATE);
        userDomain.setVerificationToken(VERIFICATION_TOKEN);
        userDomain.setProfilePicture(PROFILE_PICTURE);
        userDomain.setRole(roleDomain);
        userDomain.setVerified(VERIFIED);

        userDomains = new ArrayList<>();
        userDomains.add(userDomain);

        userEntities = new ArrayList<>();
        userEntities.add(userEntity);

        genreEntity = new GenreEntity();
        genreEntity.setId(ID);
        genreEntity.setName(NAME);

        genreDomain = new Genre();
        genreDomain.setId(ID);
        genreDomain.setName(NAME);

        genreEntities = new ArrayList<>();
        genreEntities.add(genreEntity);

        genreDomains = new ArrayList<>();
        genreDomains.add(genreDomain);

        developerEntity = new DeveloperEntity();
        developerEntity.setId(ID);
        developerEntity.setName(NAME);

        developerDomain = new Developer();
        developerDomain.setId(ID);
        developerDomain.setName(NAME);

        developerDomains = new ArrayList<>();
        developerDomains.add(developerDomain);

        developerEntities = new ArrayList<>();
        developerEntities.add(developerEntity);

        regionEntity = new RegionEntity();
        regionEntity.setId(ID);
        regionEntity.setName(NAME);

        regionDomain = new Region();
        regionDomain.setId(ID);
        regionDomain.setName(NAME);

        regionEntities = new ArrayList<>();
        regionEntities.add(regionEntity);

        regionDomains = new ArrayList<>();
        regionDomains.add(regionDomain);

        publisherEntity = new PublisherEntity();
        publisherEntity.setId(ID);
        publisherEntity.setName(NAME);

        publisherDomain = new Publisher();
        publisherDomain.setId(ID);
        publisherDomain.setName(NAME);

        publisherDomains = new ArrayList<>();
        publisherDomains.add(publisherDomain);

        publisherEntities = new ArrayList<>();
        publisherEntities.add(publisherEntity);

        platformEntity = new PlatformEntity();
        platformEntity.setId(ID);
        platformEntity.setName(NAME);

        platformDomain = new Platform();
        platformDomain.setId(ID);
        platformDomain.setName(NAME);

        platformDomains = new ArrayList<>();
        platformDomains.add(platformDomain);

        platformEntities = new ArrayList<>();
        platformEntities.add(platformEntity);

        gameEntity = new GameEntity();
        gameEntity.setId(ID);
        gameEntity.setTitle(TITLE);
        gameEntity.setCoverArt(COVER_ART);
        gameEntity.setPublisherEntitySet(new HashSet<>(Collections.singletonList(publisherEntity)));
        gameEntity.setDeveloperEntitySet(new HashSet<>(Collections.singletonList(developerEntity)));
        gameEntity.setPlatformEntitySet(new HashSet<>(Collections.singletonList(platformEntity)));
        gameEntity.setRegionEntitySet(new HashSet<>(Collections.singletonList(regionEntity)));
        gameEntity.setGenreEntitySet(new HashSet<>(Collections.singletonList(genreEntity)));

        gameDomain = new Game();
        gameDomain.setId(ID);
        gameDomain.setTitle(TITLE);
        gameDomain.setCoverArt(COVER_ART);
        gameDomain.setPublisherSet(new HashSet<>(Collections.singletonList(publisherDomain)));
        gameDomain.setDeveloperSet(new HashSet<>(Collections.singletonList(developerDomain)));
        gameDomain.setPlatformSet(new HashSet<>(Collections.singletonList(platformDomain)));
        gameDomain.setRegionSet(new HashSet<>(Collections.singletonList(regionDomain)));
        gameDomain.setGenreSet(new HashSet<>(Collections.singletonList(genreDomain)));

        gameDomains = new ArrayList<>();
        gameDomains.add(gameDomain);

        gameEntities = new ArrayList<>();
        gameEntities.add(gameEntity);

        collectionEntity = new CollectionEntity();
        collectionEntity.setId(ID);
        collectionEntity.setUser(userEntity);
        collectionEntity.setGame(gameEntity);


        collectionDomain = new Collection();
        collectionDomain.setId(ID);
        collectionDomain.setUser(userDomain);
        collectionDomain.setGame(gameDomain);

        collectionDomains = new ArrayList<>();
        collectionDomains.add(collectionDomain);

        collectionEntities = new ArrayList<>();
        collectionEntities.add(collectionEntity);

        favoriteEntity = new FavoriteEntity();
        favoriteEntity.setId(ID);
        favoriteEntity.setUser(userEntity);
        favoriteEntity.setGame(gameEntity);


        favoriteDomain = new Favorite();
        favoriteDomain.setId(ID);
        favoriteDomain.setUser(userDomain);
        favoriteDomain.setGame(gameDomain);

        favoriteDomains = new ArrayList<>();
        favoriteDomains.add(favoriteDomain);

        favoriteEntities = new ArrayList<>();
        favoriteEntities.add(favoriteEntity);

        postEntity = new PostEntity();
        postEntity.setId(ID);
        postEntity.setUser(userEntity);
        postEntity.setGame(gameEntity);
        postEntity.setCreationDate(new Date());
        postEntity.setContent(CONTENT);
        postEntity.setPicture(PICTURE);


        postDomain = new Post();
        postDomain.setId(ID);
        postDomain.setUser(userDomain);
        postDomain.setGame(gameDomain);
        postDomain.setCreationDate(new Date());
        postDomain.setContent(CONTENT);
        postDomain.setPicture(PICTURE);

        postDomains = new ArrayList<>();
        postDomains.add(postDomain);

        postEntities = new ArrayList<>();
        postEntities.add(postEntity);


        commentEntity = new CommentEntity();
        commentEntity.setId(ID);
        commentEntity.setUser(userEntity);
        commentEntity.setPost(postEntity);
        commentEntity.setContent(CONTENT);
        commentEntity.setCreationDate(new Date());

        commentDomain = new Comment();
        commentDomain.setId(ID);
        commentDomain.setUser(userDomain);
        commentDomain.setPost(postDomain);
        commentDomain.setContent(CONTENT);
        commentDomain.setCreationDate(new Date());

        commentDomains = new ArrayList<>();
        commentDomains.add(commentDomain);

        commentEntities = new ArrayList<>();
        commentEntities.add(commentEntity);
    }
}
