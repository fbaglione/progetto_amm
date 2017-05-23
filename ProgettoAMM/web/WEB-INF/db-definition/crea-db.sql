/**
 * Dati accesso DB
 *      username: 'administrator'
 *      password: 'pass'
 */

/**
 * Creazione struttura tabelle
 */

/* User */
CREATE TABLE users (
    id              INTEGER         GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username        VARCHAR(64)     UNIQUE,
    password        VARCHAR(256)    NOT NULL,
    nome            VARCHAR(64)     NOT NULL,
    cognome         VARCHAR(64),
    dataDiNascita   DATE,
    frase           VARCHAR(1024),
    urlImmagine     VARCHAR(2083)
);

CREATE TABLE friendship (
    follower        INTEGER,
    followed        INTEGER,

    FOREIGN KEY (follower) REFERENCES users(id),
    FOREIGN KEY (followed) REFERENCES users(id),
    PRIMARY KEY (follower, followed)
);

/* Gruppo */
CREATE TABLE gruppi (
    id              INTEGER         GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome            VARCHAR(64)     UNIQUE,
    urlImmagine     VARCHAR(2083),
    administrator   INTEGER,

    FOREIGN KEY (administrator) REFERENCES users(id)
);

CREATE TABLE membri_gruppo (
    gruppo          INTEGER,
    membro          INTEGER,

    FOREIGN KEY (gruppo) REFERENCES gruppi(id),
    FOREIGN KEY (membro) REFERENCES users(id),
    PRIMARY KEY (gruppo, membro)
);

/* Post */
CREATE TABLE postType (
    id              INTEGER         GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome            VARCHAR(64)     UNIQUE
);

CREATE TABLE posts (
    id              INTEGER         GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    postType        INTEGER         NOT NULL,
    autore          INTEGER         NOT NULL,
    text            VARCHAR(30000),
    content         VARCHAR(2083),
    bacheca_user    INTEGER,
    bacheca_gruppo  INTEGER,

    FOREIGN KEY (postType) REFERENCES postType(id),
    FOREIGN KEY (autore) REFERENCES users(id),
    FOREIGN KEY (bacheca_user) REFERENCES users(id),
    FOREIGN KEY (bacheca_gruppo) REFERENCES gruppi(id)
);

/**
 * Inserimento dati
 */

/* users */
INSERT INTO users (id, username, password, nome, cognome, dataDiNascita, frase, urlImmagine) VALUES
    (0, 'administrator', 'pass', 'Administrator', NULL, NULL, NULL, NULL),
    (default, 'djanni', 'pass', 'Djanni', 'Randagio', '2011-01-01', 'Datemi cibo! Miao...', 'http://scontent.cdninstagram.com/t51.2885-15/s750x750/sh0.08/e35/18160279_1496958173662222_5345517778365317120_n.jpg?ig_cache_key=MTUwMjg0NzcwNzQ1ODY5NzI4Nw%3D%3D.2'),
    (default, 'riccardo', 'pass', 'Riccardo', 'Scateni', '1961-11-06', 'W Oculus Rift!', 'https://media.licdn.com/mpr/mpr/shrinknp_200_200/p/2/000/10c/3d8/1556a8a.jpg'),
    (default, 'davide', 'pass', 'Davide', 'Spano', '2011-01-01', 'Do or do not. There is no try.', 'https://i1.rgstatic.net/ii/profile.image/AS%3A278717961130010%401443462948368_l/Lucio_Spano.png'),
    (default, 'incompleto', 'incompleto', 'Incompleto', NULL, NULL, NULL, 'http://stevewright.info/wp-content/uploads/2012/12/puzzle_incomplete-11-300x225.jpg');


/* friendship */
INSERT INTO friendship (follower, followed) VALUES
    (1, 2),(2, 1),(2, 3),(3, 2),(3, 4),(4, 3);

/* gruppi */
INSERT INTO gruppi (id, nome, urlImmagine, administrator) VALUES
    (default, 'Mongolfieristi', 'img/mongolfieristi.png', 3),
    (default, 'Animali', 'http://r.ddmcdn.com/s_f/o_1/cx_692/cy_253/cw_1472/ch_1472/w_720/APL/uploads/2014/12/aplive-african-watering-hole-cam.jpg', 1),
    (default, 'Professori', 'http://harrypottergenerationweare.weebly.com/uploads/2/5/8/7/25879359/8291598.gif', 2);

/* membri_gruppo */
INSERT INTO membri_gruppo (gruppo, membro) VALUES
    (1, 2),(1, 3),(1, 4),(2, 1),(3, 2),(3, 3);

/* postType */
INSERT INTO postType (id, nome) VALUES
    (default, 'TEXT'),(default, 'IMAGE'),(default, 'LINK');

/* post */
INSERT INTO posts (id, postType, autore, bacheca_user, bacheca_gruppo, text, content) VALUES
    (default, 2, 1, NULL, 2, 'Wow!', 'http://kids.nationalgeographic.com/content/dam/kids/photos/animals/Dinosaurs/Q-Z/Spinosaurus.ngsversion.1467372286021.adapt.1900.1.jpg'),
    (default, 2, 2, 1, NULL, 'Il miglior gatto al mondo!', 'http://scontent.cdninstagram.com/t51.2885-15/s750x750/sh0.08/e35/18014025_292883181123886_5187515530797383680_n.jpg?ig_cache_key=MTUwMjE2Njg3MzE1MjAyNjU5Mg%3D%3D.2'),
    (default, 3, 2, 3, NULL, 'Ottimo sito che fornisce dummy text.', 'http://www.lipsum.com/'),
    (default, 3, 3, NULL, 3, 'jQuery is a fast, small, and feature-rich JavaScript library. It makes things like HTML document traversal and manipulation, event handling, animation, and Ajax much simpler with an easy-to-use API that works across a multitude of browsers. With a combination of versatility and extensibility, jQuery has changed the way that millions of people write JavaScript.', 'https://jquery.com/'),
    (default, 1, 2, NULL, 3, 'Virtual reality (VR) typically refers to computer technologies that use virtual reality headsets, sometimes in combination with physical spaces or multi-projected environments, to generate realistic images, sounds and other sensations that simulates a user''s physical presence in a virtual or imaginary environment. A person using virtual reality equipment is able to "look around" the artificial world, and with high quality VR move about in it and interact with virtual features or items. VR headsets are head-mounted goggles with a screen in front of the eyes. Programs may include audio and sounds through speakers or headphones.', NULL),
    (default, 2, 3, NULL, 1, 'Bellissima esibizione!', 'http://walesinternationalballoonfestival.co.uk/wp-content/uploads/2016/06/Hot-Air-Balloons-1024x640.jpg');