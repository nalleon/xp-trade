package es.iespuertodelacruz.xptrade.utilities;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.dto.input.*;
import es.iespuertodelacruz.xptrade.dto.output.*;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class MapperInputDTOHelper extends TestUtilities {
    /**
     * Properties
     */
    public RoleOutputDTO roleOutputDTO;
    public Role roleDomain;

    public List<Role> roleDomains;


    public RoleInputDTO roleInputDTO;
    public List<RoleInputDTO> roleInputDTOList;

    public UserDTO userDTO;
    public User userDomain;
    public List<UserDTO> userDTOList;
    public List<User> userDomains;

    public GenreInputDTO genreInputDTO;
    public Genre genreDomain;
    public List<GenreInputDTO> genreInputDTOList;
    public List<Genre> genreDomains;

    public PublisherInputDTO publisherInputDTO;
    public Publisher publisherDomain;
    public List<PublisherInputDTO> publisherInputDTOList;
    public List<Publisher> publisherDomains;

    public DeveloperInputDTO developerInputDTO;
    public Developer developerDomain;
    public List<DeveloperInputDTO> developerInputDTOList;
    public List<Developer> developerDomains;

    public PlatformInputDTO platformInputDTO;
    public Platform platformDomain;
    public List<PlatformInputDTO> platformInputDTOList;
    public List<Platform> platformDomains;

    public RegionInputDTO regionInputDTO;
    public Region regionDomain;
    public List<RegionInputDTO> regionInputDTOList;
    public List<Region> regionDomains;

    public GameOutputDTO gameOutputDTO;
    public Game gameDomain;
    public List<GameOutputDTO> gameOutputDTOList;
    public List<Game> gameDomains;

    public GameInputDTO gameInputDTO;
    public List<GameInputDTO> gameInputDTOList;

    public CollectionInputDTO collectionInputDTO;
    public Collection collectionDomain;
    public List<CollectionInputDTO> collectionInputDTOList;
    public List<Collection> collectionDomains;

    public FavoriteInputDTO favoriteInputDTO;
    public Favorite favoriteDomain;
    public List<FavoriteInputDTO> favoriteInputDTOList;
    public List<Favorite> favoriteDomains;

    public PostInputDTO postInputDTO;
    public Post postDomain;
    public List<PostInputDTO> postInputDTOList;
    public List<Post> postDomains;

    public CommentInputDTO commentInputDTO;
    public Comment commentDomain;
    public List<CommentInputDTO> commentInputDTOList;
    public List<Comment> commentDomains;

    public TagInputDTO tagInputDTO;
    public Tag tagDomain;
    public List<TagInputDTO> tagInputDTOList;
    public List<Tag> tagDomains;

    public GameCollectionInputDTO gameCollectionInputDTO;
    public GameCollection gameCollection;
    public List<GameCollectionInputDTO> gameCollectionInputDTOList;
    public List<GameCollection> gameCollectionList;

    @BeforeEach
    public void beforeEach() {

        roleOutputDTO = new RoleOutputDTO(ID, ROLE_NAME);

        roleInputDTO = new RoleInputDTO(ROLE_NAME);

        roleInputDTOList = new ArrayList<>();
        roleInputDTOList.add(roleInputDTO);

        roleDomain = new Role();
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

        genreInputDTO = new GenreInputDTO(NAME);

        genreDomain = new Genre();
        genreDomain.setName(NAME);

        genreInputDTOList = new ArrayList<>();
        genreInputDTOList.add(genreInputDTO);

        tagInputDTO = new TagInputDTO(NAME);

        tagDomain = new Tag(ID, NAME);
        tagDomain.setName(NAME);

        tagInputDTOList = new ArrayList<>();
        tagInputDTOList.add(tagInputDTO);

        tagDomains = new ArrayList<>();
        tagDomains.add(tagDomain);

        genreDomains = new ArrayList<>();
        genreDomains.add(genreDomain);

        developerInputDTO = new DeveloperInputDTO(NAME);

        developerDomain = new Developer();
        developerDomain.setName(NAME);

        developerDomains = new ArrayList<>();
        developerDomains.add(developerDomain);

        developerInputDTOList = new ArrayList<>();
        developerInputDTOList.add(developerInputDTO);

        regionInputDTO = new RegionInputDTO(NAME);

        regionDomain = new Region();
        regionDomain.setName(NAME);

        regionInputDTOList = new ArrayList<>();
        regionInputDTOList.add(regionInputDTO);

        regionDomains = new ArrayList<>();
        regionDomains.add(regionDomain);

        publisherInputDTO = new PublisherInputDTO(NAME);


        publisherDomain = new Publisher();
        publisherDomain.setName(NAME);

        publisherDomains = new ArrayList<>();
        publisherDomains.add(publisherDomain);

        publisherInputDTOList = new ArrayList<>();
        publisherInputDTOList.add(publisherInputDTO);

        platformInputDTO = new PlatformInputDTO(NAME);

        platformDomain = new Platform();
        platformDomain.setName(NAME);

        platformDomains = new ArrayList<>();
        platformDomains.add(platformDomain);

        platformInputDTOList = new ArrayList<>();
        platformInputDTOList.add(platformInputDTO);

        gameOutputDTO = new GameOutputDTO(ID,TITLE, COVER_ART, SLUG, RATING, RELEASED,
                    new HashSet<>(Collections.singletonList(new TagOutputDTO(ID, NAME))),
                    new HashSet<>(Collections.singletonList(new DeveloperOutputDTO(ID,NAME))),
                    new HashSet<>(Collections.singletonList( new GenreOutputDTO(ID, NAME))),
                    new HashSet<>(Collections.singletonList(new PlatformOutputDTO(ID, NAME))),
                    new HashSet<>(Collections.singletonList(new PublisherOutputDTO(ID,NAME)))
                );

        gameInputDTO = new GameInputDTO(TITLE, COVER_ART, SLUG,
                RATING, RELEASED,
                new HashSet<>(Collections.singletonList(tagInputDTO)),
                new HashSet<>(Collections.singletonList(developerInputDTO)),
                new HashSet<>(Collections.singletonList(genreInputDTO)),
                new HashSet<>(Collections.singletonList(platformInputDTO)),
                new HashSet<>(Collections.singletonList(publisherInputDTO))
        );

        gameDomain = new Game();
        gameDomain.setTitle(TITLE);
        gameDomain.setCoverArt(COVER_ART);
        gameDomain.setSlug(SLUG);
        gameDomain.setPublisherSet(new HashSet<>(Collections.singletonList(publisherDomain)));
        gameDomain.setDeveloperSet(new HashSet<>(Collections.singletonList(developerDomain)));
        gameDomain.setPlatformSet(new HashSet<>(Collections.singletonList(platformDomain)));
        gameDomain.setGenreSet(new HashSet<>(Collections.singletonList(genreDomain)));
        gameDomain.setTagSet(new HashSet<>(Collections.singletonList(tagDomain)));
        gameDomain.setRating(RATING);
        gameDomain.setReleased(RELEASED);

        gameDomains = new ArrayList<>();
        gameDomains.add(gameDomain);

        gameOutputDTOList = new ArrayList<>();
        gameOutputDTOList.add(gameOutputDTO);

        gameInputDTOList = new ArrayList<>();
        gameInputDTOList.add(gameInputDTO);

        gameCollectionInputDTO = new GameCollectionInputDTO(gameInputDTO, regionInputDTO, platformInputDTO);
        gameCollectionInputDTOList = new ArrayList<>();
        gameCollectionInputDTOList.add(gameCollectionInputDTO);

        collectionInputDTO = new CollectionInputDTO(userDTO);

        collectionDomain = new Collection();
        collectionDomain.setUser(userDomain);

        collectionDomains = new ArrayList<>();
        collectionDomains.add(collectionDomain);

        collectionInputDTOList = new ArrayList<>();
        collectionInputDTOList.add(collectionInputDTO);

        favoriteInputDTO = new FavoriteInputDTO(gameInputDTO, userDTO);

        favoriteDomain = new Favorite();
        favoriteDomain.setUser(userDomain);
        favoriteDomain.setGame(gameDomain);

        favoriteDomains = new ArrayList<>();
        favoriteDomains.add(favoriteDomain);

        favoriteInputDTOList = new ArrayList<>();
        favoriteInputDTOList.add(favoriteInputDTO);

        postInputDTO = new PostInputDTO(gameInputDTO, userDTO, CONTENT, PICTURE);

        postDomain = new Post();
        postDomain.setUser(userDomain);
        postDomain.setGame(gameDomain);
        postDomain.setCreationDate(CREATION_DATE);
        postDomain.setContent(CONTENT);
        postDomain.setPicture(PICTURE);

        postDomains = new ArrayList<>();
        postDomains.add(postDomain);

        postInputDTOList = new ArrayList<>();
        postInputDTOList.add(postInputDTO);

        commentInputDTO = new CommentInputDTO(
                new PostOutputDTO(ID, gameOutputDTO, userDTO, CONTENT, PICTURE, CREATION_DATE), userDTO, CONTENT);

        commentDomain = new Comment();
        commentDomain.setUser(userDomain);
        commentDomain.setPost(postDomain);
        commentDomain.setContent(CONTENT);
        commentDomain.setCreationDate(CREATION_DATE);

        commentDomains = new ArrayList<>();
        commentDomains.add(commentDomain);

        commentInputDTOList = new ArrayList<>();
        commentInputDTOList.add(commentInputDTO);
    }
}
