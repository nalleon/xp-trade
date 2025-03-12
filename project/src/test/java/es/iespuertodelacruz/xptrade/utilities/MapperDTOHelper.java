package es.iespuertodelacruz.xptrade.utilities;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.dto.*;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserSearchDTO;
import es.iespuertodelacruz.xptrade.model.entities.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

public class MapperDTOHelper extends TestUtilities {
    /**
     * Properties
     */
    public RoleDTO roleDTO;
    public Role roleDomain;
    public List<RoleDTO> roleDTOList;
    public List<Role> roleDomains;

    public UserDTO userDTO;
    public User userDomain;
    public List<UserDTO> userDTOList;
    public List<User> userDomains;

    public GenreDTO genreDTO;
    public Genre genreDomain;
    public List<GenreDTO> genreDTOList;
    public List<Genre> genreDomains;

    public PublisherDTO publisherDTO;
    public Publisher publisherDomain;
    public List<PublisherDTO> publisherDTOList;
    public List<Publisher> publisherDomains;

    public DeveloperDTO developerDTO;
    public Developer developerDomain;
    public List<DeveloperDTO> developerDTOList;
    public List<Developer> developerDomains;

    public PlatformDTO platformDTO;
    public Platform platformDomain;
    public List<PlatformDTO> platformDTOList;
    public List<Platform> platformDomains;

    public RegionDTO regionDTO;
    public Region regionDomain;
    public List<RegionDTO> regionDTOList;
    public List<Region> regionDomains;

    public GameDTO gameDTO;
    public Game gameDomain;
    public List<GameDTO> gameDTOList;
    public List<Game> gameDomains;

    public CollectionDTO collectionDTO;
    public Collection collectionDomain;
    public List<CollectionDTO> collectionDTOList;
    public List<Collection> collectionDomains;

    public FavoriteDTO favoriteDTO;
    public Favorite favoriteDomain;
    public List<FavoriteDTO> favoriteDTOList;
    public List<Favorite> favoriteDomains;

    public PostDTO postDTO;
    public Post postDomain;
    public List<PostDTO> postDTOList;
    public List<Post> postDomains;

    public CommentDTO commentDTO;
    public Comment commentDomain;
    public List<CommentDTO> commentDTOList;
    public List<Comment> commentDomains;



    @BeforeEach
    public void beforeEach() {
        roleDTO = new RoleDTO(ID,ROLE_NAME);

        roleDTOList = new ArrayList<>();
        roleDTOList.add(roleDTO);

        roleDomain = new Role();
        roleDomain.setId(ID);
        roleDomain.setName(ROLE_NAME);

        roleDomains = new ArrayList<>();
        roleDomains.add(roleDomain);


        userDTO = new UserDTO(ID, USERNAME,PASSWORD, EMAIL, roleDTO, VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE);

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

        genreDTO = new GenreDTO(ID, NAME);


        genreDomain = new Genre();
        genreDomain.setId(ID);
        genreDomain.setName(NAME);

        genreDTOList = new ArrayList<>();
        genreDTOList.add(genreDTO);

        genreDomains = new ArrayList<>();
        genreDomains.add(genreDomain);

        developerDTO = new DeveloperDTO(ID, NAME);

        developerDomain = new Developer();
        developerDomain.setId(ID);
        developerDomain.setName(NAME);

        developerDomains = new ArrayList<>();
        developerDomains.add(developerDomain);

        developerDTOList = new ArrayList<>();
        developerDTOList.add(developerDTO);

        regionDTO = new RegionDTO(ID, NAME);

        regionDomain = new Region();
        regionDomain.setId(ID);
        regionDomain.setName(NAME);

        regionDTOList = new ArrayList<>();
        regionDTOList.add(regionDTO);

        regionDomains = new ArrayList<>();
        regionDomains.add(regionDomain);

        publisherDTO = new PublisherDTO(ID, NAME);


        publisherDomain = new Publisher();
        publisherDomain.setId(ID);
        publisherDomain.setName(NAME);

        publisherDomains = new ArrayList<>();
        publisherDomains.add(publisherDomain);

        publisherDTOList = new ArrayList<>();
        publisherDTOList.add(publisherDTO);

        platformDTO = new PlatformDTO(ID, NAME);

        platformDomain = new Platform();
        platformDomain.setId(ID);
        platformDomain.setName(NAME);

        platformDomains = new ArrayList<>();
        platformDomains.add(platformDomain);

        platformDTOList = new ArrayList<>();
        platformDTOList.add(platformDTO);

        gameDTO = new GameDTO(ID, TITLE, COVER_ART,new HashSet<>(Collections.singletonList(developerDTO)),
                    new HashSet<>(Collections.singletonList(genreDTO)),
                    new HashSet<>(Collections.singletonList(platformDTO)),
                    new HashSet<>(Collections.singletonList(publisherDTO)),
                    new HashSet<>(Collections.singletonList(regionDTO))
                );

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

        gameDTOList = new ArrayList<>();
        gameDTOList.add(gameDTO);

        collectionDTO = new CollectionDTO(ID, gameDTO, userDTO);

        collectionDomain = new Collection();
        collectionDomain.setId(ID);
        collectionDomain.setUser(userDomain);
        collectionDomain.setGame(gameDomain);

        collectionDomains = new ArrayList<>();
        collectionDomains.add(collectionDomain);

        collectionDTOList = new ArrayList<>();
        collectionDTOList.add(collectionDTO);

        favoriteDTO = new FavoriteDTO(ID, gameDTO, userDTO);

        favoriteDomain = new Favorite();
        favoriteDomain.setId(ID);
        favoriteDomain.setUser(userDomain);
        favoriteDomain.setGame(gameDomain);

        favoriteDomains = new ArrayList<>();
        favoriteDomains.add(favoriteDomain);

        favoriteDTOList = new ArrayList<>();
        favoriteDTOList.add(favoriteDTO);

        postDTO = new PostDTO(ID, gameDTO, userDTO, CONTENT, PICTURE, CREATION_DATE);

        postDomain = new Post();
        postDomain.setId(ID);
        postDomain.setUser(userDomain);
        postDomain.setGame(gameDomain);
        postDomain.setCreationDate(CREATION_DATE);
        postDomain.setContent(CONTENT);
        postDomain.setPicture(PICTURE);

        postDomains = new ArrayList<>();
        postDomains.add(postDomain);

        postDTOList = new ArrayList<>();
        postDTOList.add(postDTO);


        commentDTO = new CommentDTO(ID, postDTO, userDTO, CONTENT, CREATION_DATE);

        commentDomain = new Comment();
        commentDomain.setId(ID);
        commentDomain.setUser(userDomain);
        commentDomain.setPost(postDomain);
        commentDomain.setContent(CONTENT);
        commentDomain.setCreationDate(CREATION_DATE);

        commentDomains = new ArrayList<>();
        commentDomains.add(commentDomain);

        commentDTOList = new ArrayList<>();
        commentDTOList.add(commentDTO);
    }
}
