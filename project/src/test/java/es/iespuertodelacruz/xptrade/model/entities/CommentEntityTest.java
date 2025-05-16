package es.iespuertodelacruz.xptrade.model.entities;

import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CommentEntityTest extends MapperHelper {
    CommentEntity item;
    PostEntity post;
    GameEntity game;
    UserEntity user;
    GenreEntity genre;
    RegionEntity region;
    PublisherEntity publisher;
    DeveloperEntity developer;
    PlatformEntity platform;
    Set<DeveloperEntity> developerSet;
    Set<GenreEntity> genreSet;
    Set<PlatformEntity> platformSet;
    Set<PublisherEntity> publisherSet;
    Set<RegionEntity> regionSet;
    CommentEntity repliedComment;
    List<CommentEntity> replies;

    @BeforeEach
    public void beforeEach() {
        genre = new GenreEntity(NAME);
        region = new RegionEntity(NAME);
        publisher = new PublisherEntity(NAME);
        developer = new DeveloperEntity(NAME);
        platform = new PlatformEntity(NAME);

        genre.setId(ID);
        region.setId(ID);
        publisher.setId(ID);
        developer.setId(ID);
        platform.setId(ID);

        developerSet = new HashSet<>(Collections.singletonList(developer));
        genreSet = new HashSet<>(Collections.singletonList(genre));
        platformSet = new HashSet<>(Collections.singletonList(platform));
        publisherSet = new HashSet<>(Collections.singletonList(publisher));
        regionSet = new HashSet<>(Collections.singletonList(region));

        game = new GameEntity(TITLE, COVER_ART, SLUG, developerSet, genreSet, platformSet, publisherSet);
        game.setId(ID);
        user = new UserEntity(ID);
        post = new PostEntity();
        post.setId(ID);
        post.setPicture(PICTURE);
        post.setGame(game);
        post.setContent(CONTENT);
        post.setCreationDate(CREATION_DATE);
        post.setUser(user);

        repliedComment = new CommentEntity(2);
        repliedComment.setUser(user);
        repliedComment.setPost(post);
        repliedComment.setContent(CONTENT);
        repliedComment.setCreationDate(CREATION_DATE);



        item = new CommentEntity(ID);
        item.setUser(user);
        item.setPost(post);
        item.setContent(CONTENT);
        item.setCreationDate(CREATION_DATE);
        item.setRepliedComment(repliedComment);

        replies = new ArrayList<>();
        replies.add(item);
        repliedComment.setReplies(replies);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(post, item.getPost(), MESSAGE_ERROR);
        Assertions.assertEquals(user, item.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(CONTENT, item.getContent(), MESSAGE_ERROR);
        Assertions.assertEquals(CREATION_DATE, item.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(repliedComment, item.getRepliedComment(), MESSAGE_ERROR);
        Assertions.assertEquals(replies, repliedComment.getReplies(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(game.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(user.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(CONTENT), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(PICTURE), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        CommentEntity equals = new CommentEntity(post, user, CONTENT);
        equals.setId(ID);
        CommentEntity differentId = new CommentEntity(2);
        String str = "str";
        CommentEntity nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
