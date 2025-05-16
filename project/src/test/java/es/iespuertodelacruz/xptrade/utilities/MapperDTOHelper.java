package es.iespuertodelacruz.xptrade.utilities;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.dto.output.*;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

public class MapperDTOHelper extends TestUtilities {
    /**
     * Properties
     */
    public RoleOutputDTO roleOutputDTO;
    public Role roleDomain;
    public List<RoleOutputDTO> roleOutputDTOList;
    public List<Role> roleDomains;

    public UserDTO userDTO;
    public User userDomain;
    public List<UserDTO> userDTOList;
    public List<User> userDomains;

    public GenreOutputDTO genreOutputDTO;
    public Genre genreDomain;
    public List<GenreOutputDTO> genreOutputDTOList;
    public List<Genre> genreDomains;

    public PublisherOutputDTO publisherOutputDTO;
    public Publisher publisherDomain;
    public List<PublisherOutputDTO> publisherOutputDTOList;
    public List<Publisher> publisherDomains;

    public DeveloperOutputDTO developerOutputDTO;
    public Developer developerDomain;
    public List<DeveloperOutputDTO> developerOutputDTOList;
    public List<Developer> developerDomains;

    public PlatformOutputDTO platformOutputDTO;
    public Platform platformDomain;
    public List<PlatformOutputDTO> platformOutputDTOList;
    public List<Platform> platformDomains;

    public RegionOutputDTO regionOutputDTO;
    public Region regionDomain;
    public List<RegionOutputDTO> regionOutputDTOList;
    public List<Region> regionDomains;

    public GameOutputDTO gameOutputDTO;
    public Game gameDomain;
    public List<GameOutputDTO> gameOutputDTOList;
    public List<Game> gameDomains;

    public CollectionOutputDTO collectionOutputDTO;
    public Collection collectionDomain;
    public List<CollectionOutputDTO> collectionOutputDTOList;
    public List<Collection> collectionDomains;

    public FavoriteOutputDTO favoriteOutputDTO;
    public Favorite favoriteDomain;
    public List<FavoriteOutputDTO> favoriteOutputDTOList;
    public List<Favorite> favoriteDomains;

    public PostOutputDTO postOutputDTO;
    public Post postDomain;
    public List<PostOutputDTO> postOutputDTOList;
    public List<Post> postDomains;

    public CommentOutputDTO commentOutputDTO;
    public Comment commentDomain;
    public List<CommentOutputDTO> commentOutputDTOList;
    public List<Comment> commentDomains;



    @BeforeEach
    public void beforeEach() {
        roleOutputDTO = new RoleOutputDTO(ID,ROLE_NAME);

        roleOutputDTOList = new ArrayList<>();
        roleOutputDTOList.add(roleOutputDTO);

        roleDomain = new Role();
        roleDomain.setId(ID);
        roleDomain.setName(ROLE_NAME);

        roleDomains = new ArrayList<>();
        roleDomains.add(roleDomain);


        userDTO = new UserDTO(ID, USERNAME,PASSWORD, EMAIL, roleOutputDTO, VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE);

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

        userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);

        genreOutputDTO = new GenreOutputDTO(ID, NAME);


        genreDomain = new Genre();
        genreDomain.setId(ID);
        genreDomain.setName(NAME);

        genreOutputDTOList = new ArrayList<>();
        genreOutputDTOList.add(genreOutputDTO);

        genreDomains = new ArrayList<>();
        genreDomains.add(genreDomain);

        developerOutputDTO = new DeveloperOutputDTO(ID, NAME);

        developerDomain = new Developer();
        developerDomain.setId(ID);
        developerDomain.setName(NAME);

        developerDomains = new ArrayList<>();
        developerDomains.add(developerDomain);

        developerOutputDTOList = new ArrayList<>();
        developerOutputDTOList.add(developerOutputDTO);

        regionOutputDTO = new RegionOutputDTO(ID, NAME);

        regionDomain = new Region();
        regionDomain.setId(ID);
        regionDomain.setName(NAME);

        regionOutputDTOList = new ArrayList<>();
        regionOutputDTOList.add(regionOutputDTO);

        regionDomains = new ArrayList<>();
        regionDomains.add(regionDomain);

        publisherOutputDTO = new PublisherOutputDTO(ID, NAME);


        publisherDomain = new Publisher();
        publisherDomain.setId(ID);
        publisherDomain.setName(NAME);

        publisherDomains = new ArrayList<>();
        publisherDomains.add(publisherDomain);

        publisherOutputDTOList = new ArrayList<>();
        publisherOutputDTOList.add(publisherOutputDTO);

        platformOutputDTO = new PlatformOutputDTO(ID, NAME);

        platformDomain = new Platform();
        platformDomain.setId(ID);
        platformDomain.setName(NAME);

        platformDomains = new ArrayList<>();
        platformDomains.add(platformDomain);

        platformOutputDTOList = new ArrayList<>();
        platformOutputDTOList.add(platformOutputDTO);

        gameOutputDTO = new GameOutputDTO(ID, TITLE, COVER_ART, SLUG, new HashSet<>(Collections.singletonList(developerOutputDTO)),
                    new HashSet<>(Collections.singletonList(genreOutputDTO)),
                    new HashSet<>(Collections.singletonList(platformOutputDTO)),
                    new HashSet<>(Collections.singletonList(publisherOutputDTO))
                );

        gameDomain = new Game();
        gameDomain.setId(ID);
        gameDomain.setTitle(TITLE);
        gameDomain.setCoverArt(COVER_ART);
        gameDomain.setSlug(SLUG);
        gameDomain.setPublisherSet(new HashSet<>(Collections.singletonList(publisherDomain)));
        gameDomain.setDeveloperSet(new HashSet<>(Collections.singletonList(developerDomain)));
        gameDomain.setPlatformSet(new HashSet<>(Collections.singletonList(platformDomain)));
        gameDomain.setGenreSet(new HashSet<>(Collections.singletonList(genreDomain)));

        gameDomains = new ArrayList<>();
        gameDomains.add(gameDomain);

        gameOutputDTOList = new ArrayList<>();
        gameOutputDTOList.add(gameOutputDTO);

        collectionOutputDTO = new CollectionOutputDTO(ID, gameOutputDTO, userDTO);

        collectionDomain = new Collection();
        collectionDomain.setId(ID);
        collectionDomain.setUser(userDomain);
        collectionDomain.setGame(gameDomain);

        collectionDomains = new ArrayList<>();
        collectionDomains.add(collectionDomain);

        collectionOutputDTOList = new ArrayList<>();
        collectionOutputDTOList.add(collectionOutputDTO);

        favoriteOutputDTO = new FavoriteOutputDTO(ID, gameOutputDTO, userDTO);

        favoriteDomain = new Favorite();
        favoriteDomain.setId(ID);
        favoriteDomain.setUser(userDomain);
        favoriteDomain.setGame(gameDomain);

        favoriteDomains = new ArrayList<>();
        favoriteDomains.add(favoriteDomain);

        favoriteOutputDTOList = new ArrayList<>();
        favoriteOutputDTOList.add(favoriteOutputDTO);

        postOutputDTO = new PostOutputDTO(ID, gameOutputDTO, userDTO, CONTENT, PICTURE, CREATION_DATE);

        postDomain = new Post();
        postDomain.setId(ID);
        postDomain.setUser(userDomain);
        postDomain.setGame(gameDomain);
        postDomain.setCreationDate(CREATION_DATE);
        postDomain.setContent(CONTENT);
        postDomain.setPicture(PICTURE);

        postDomains = new ArrayList<>();
        postDomains.add(postDomain);

        postOutputDTOList = new ArrayList<>();
        postOutputDTOList.add(postOutputDTO);


        commentOutputDTO = new CommentOutputDTO(ID, postOutputDTO, userDTO, CONTENT, CREATION_DATE);

        commentDomain = new Comment();
        commentDomain.setId(ID);
        commentDomain.setUser(userDomain);
        commentDomain.setPost(postDomain);
        commentDomain.setContent(CONTENT);
        commentDomain.setCreationDate(CREATION_DATE);

        commentDomains = new ArrayList<>();
        commentDomains.add(commentDomain);

        commentOutputDTOList = new ArrayList<>();
        commentOutputDTOList.add(commentOutputDTO);
    }
}
