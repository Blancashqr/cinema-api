
create table cinema (
    id int NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
)

CREATE TABLE Movie
(
    id int NOT NULL,
    name varchar(255) NOT NULL,
    release_date varchar(255) NOT NULL,
    genre varchar(255) NOT NULL,
    tresde bool NOT NULL,
    rating varchar(255) NOT NULL,
    duration int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE cinema_movie
(
    movie_id int NOT NULL,
    cinema_id int NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES Movie(id),
    FOREIGN KEY (cinema_id) REFERENCES Cinema(id),
    PRIMARY KEY (movie_id, cinema_id)
);

CREATE TABLE Client
(
    id int NOT NULL,
    name varchar(255) NOT NULL,
    age int NOT NULL,
    email varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Ticket
(
    id int NOT NULL,
    price int NOT NULL,
    seats int NOT NULL,
    movie_id int NOT NULL,
    client_id int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (movie_id) REFERENCES Movie(id),
    FOREIGN KEY (client_id) REFERENCES Client(id)
);

CREATE TABLE Staff
(
    id int NOT NULL,
    name varchar(255) NOT NULL,
    salary int NOT NULL,
    cinema_id int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (cinema_id) REFERENCES Cinema(id)
);
