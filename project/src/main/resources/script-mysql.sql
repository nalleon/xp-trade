CREATE TABLE `roles` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name CHAR(45) UNIQUE NOT NULL,
    CONSTRAINT `pk_roles` PRIMARY KEY (id)
);

INSERT INTO `roles` (`name`) VALUES 
    ('ROLE_ADMIN'),
    ('ROLE_USER');

CREATE TABLE `users` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    username CHAR(45) UNIQUE NOT NULL,
    password CHAR(200) NOT NULL,
    email CHAR(100) UNIQUE NOT NULL,
    role_id INTEGER,
    verified TINYINT(1) DEFAULT 0,
    verification_token CHAR(255),
    creation_date BIGINT NOT NULL,
    profile_picture CHAR(255) NULL,
    CONSTRAINT `pk_users` PRIMARY KEY (id),
    CONSTRAINT `fk_users_roles` FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT INTO `users` (
    `username`,
    `password`,
    `email`,
    `role_id`,
    `verified`,
    `verification_token`,
    `creation_date`,
    `profile_picture`
) VALUES 
    (
        'root',
        '$2a$10$P0fZ.FcD.rBwolLS9P5bAOZETI3K9E5JsiE/NQC82HgkXccYnFvry',
        'nlamail1529@gmail.com',
        1,
        1,
        'readumineko',
        UNIX_TIMESTAMP(),
        NULL
    ),
    (
        'nalleon',
        '$2a$10$P0fZ.FcD.rBwolLS9P5bAOZETI3K9E5JsiE/NQC82HgkXccYnFvry',
        'nabil14716@gmail.com',
        2,
        1,
        'ef34fd1b-c8da-4397-9d7e-06b554a2d617',
        UNIX_TIMESTAMP(),
        NULL
    );

CREATE TABLE `games` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    title CHAR(100) NOT NULL,
    cover_art CHAR(255) NULL,
    slug CHAR(100) NOT NULL,
    rating INTEGER NULL,
    released CHAR(100) NULL,
    CONSTRAINT `pk_games` PRIMARY KEY (id),
    UNIQUE KEY `uq_games_title_slug` (title, slug)
);

CREATE TABLE `tags` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name CHAR(100) NOT NULL,
    CONSTRAINT `pk_tags` PRIMARY KEY (id)
);

CREATE TABLE `games_tags` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    game_id INTEGER NOT NULL,
    tag_id INTEGER NOT NULL,
    CONSTRAINT `pk_games_tags` PRIMARY KEY (id),
    CONSTRAINT `fk_games_tags_games` FOREIGN KEY (game_id) REFERENCES games(id),
    CONSTRAINT `fk_games_tags_tags` FOREIGN KEY (tag_id) REFERENCES tags(id)
);

CREATE TABLE `platforms` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name CHAR(50) UNIQUE NOT NULL,
    CONSTRAINT `pk_platforms` PRIMARY KEY (id),
    UNIQUE KEY `uq_platforms_name` (name)
);

CREATE TABLE `games_platforms` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    game_id INTEGER NOT NULL,
    platform_id INTEGER NOT NULL,
    CONSTRAINT `pk_games_platforms` PRIMARY KEY (id),
    CONSTRAINT `fk_games_platforms_game` FOREIGN KEY (game_id) REFERENCES games(id),
    CONSTRAINT `fk_games_platforms_platform` FOREIGN KEY (platform_id) REFERENCES platforms(id),
    UNIQUE KEY `uq_games_platforms` (game_id, platform_id)
);

CREATE TABLE `regions` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name CHAR(50) UNIQUE NOT NULL,
    CONSTRAINT `pk_regions` PRIMARY KEY (id),
    UNIQUE KEY `uq_regions_name` (name)
);

CREATE TABLE `publishers` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name CHAR(50) UNIQUE NOT NULL,
    CONSTRAINT `pk_publisher` PRIMARY KEY (id),
    UNIQUE KEY `uq_publisher_name` (name)
);

CREATE TABLE `games_publishers` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    game_id INTEGER NOT NULL,
    publisher_id INTEGER NOT NULL,
    CONSTRAINT `pk_games_publishers` PRIMARY KEY (id),
    CONSTRAINT `fk_games_publishers1` FOREIGN KEY (game_id) REFERENCES games(id),
    CONSTRAINT `fk_games_publishers2` FOREIGN KEY (publisher_id) REFERENCES publishers(id),
    UNIQUE KEY `uq_games_publishers` (game_id, publisher_id)
);


CREATE TABLE `developers` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name CHAR(50) UNIQUE NOT NULL,
    CONSTRAINT `pk_developers` PRIMARY KEY (id),
    UNIQUE KEY `uq_developers_name` (name)
);

CREATE TABLE `games_developers` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    game_id INTEGER NOT NULL,
    developers_id INTEGER NOT NULL,
    CONSTRAINT `pk_games_developers` PRIMARY KEY (id),
    CONSTRAINT `fk_games_developers1` FOREIGN KEY (game_id) REFERENCES games(id),
    CONSTRAINT `fk_games_developers2` FOREIGN KEY (developers_id) REFERENCES developers(id),
    UNIQUE KEY `uq_games_developers` (game_id, developers_id)
);

CREATE TABLE `genres` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name CHAR(50) UNIQUE NOT NULL,
    CONSTRAINT `pk_genres` PRIMARY KEY (id)
);

CREATE TABLE `games_genres` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    game_id INTEGER NOT NULL,
    genre_id INTEGER NOT NULL,
    CONSTRAINT `pk_games_genres` PRIMARY KEY (id),
    CONSTRAINT `fk_games_genres_game` FOREIGN KEY (game_id) REFERENCES games(id),
    CONSTRAINT `fk_games_genres_genre` FOREIGN KEY (genre_id) REFERENCES genres(id),
    UNIQUE KEY `uq_games_genres` (game_id, genre_id)
);

CREATE TABLE `posts` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    game_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    content CHAR(255) NOT NULL,
    picture CHAR(255) NULL,
    creation_date BIGINT NOT NULL,
    CONSTRAINT `pk_posts` PRIMARY KEY (id),
    CONSTRAINT `fk_posts1` FOREIGN KEY (game_id) REFERENCES games(id),
    CONSTRAINT `fk_posts2` FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE `collections` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT `pk_collections` PRIMARY KEY (id),
    CONSTRAINT `fk_collections2` FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE `games_collections` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    game_id INTEGER NOT NULL,
    collection_id INTEGER NOT NULL,
    region_id INTEGER NOT NULL,
    platform_id INTEGER NOT NULL,
    CONSTRAINT `pk_games_collections` PRIMARY KEY (id),
    CONSTRAINT `fk_games_collections_games` FOREIGN KEY (game_id) REFERENCES games(id),
    CONSTRAINT `fk_games_collections_collections` FOREIGN KEY (collection_id) REFERENCES collections(id),
    CONSTRAINT `fk_games_collections_regions` FOREIGN KEY (region_id) REFERENCES regions(id),
    CONSTRAINT `fk_games_collections_platforms` FOREIGN KEY (platform_id) REFERENCES platforms(id)
);

CREATE TABLE `favorites` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    game_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT `pk_favorites` PRIMARY KEY (id),
    CONSTRAINT `fk_favorites` FOREIGN KEY (game_id) REFERENCES games(id),
    CONSTRAINT `fk_favorites2` FOREIGN KEY (user_id) REFERENCES users(id),
    UNIQUE KEY `uq_favorites` (game_id, user_id)
);

CREATE TABLE `comments` (
    id INTEGER AUTO_INCREMENT NOT NULL,
    post_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    replied_comment_id INTEGER NULL,
    content VARCHAR(255) NOT NULL,
    creation_date BIGINT NOT NULL,
    CONSTRAINT `pk_comments` PRIMARY KEY (id),
    CONSTRAINT `fk_comments1` FOREIGN KEY (post_id) REFERENCES posts(id),
    CONSTRAINT `fk_comments2` FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT `fk_comments3` FOREIGN KEY (replied_comment_id) REFERENCES comments(id)
);