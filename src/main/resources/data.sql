
INSERT INTO cinema (id, name) VALUES (1, 'Canary Wharf');
INSERT INTO cinema (id, name) VALUES (2, 'Westferry');
INSERT INTO cinema (id, name) VALUES (3, 'Central London');
INSERT INTO cinema (id, name) VALUES (4, 'O2');
INSERT INTO cinema (id, name) VALUES (5, 'Whitechapel');
INSERT INTO cinema (id, name) VALUES (6, 'Stratford');

INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (1, 'Avatar', '2023-23-01', 3, 1, 'PG-13', 180);
INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (2, 'Narnia', '2007-23-01', 3, 0, 'PG', 120);
INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (3, 'Die Hard', '1990-23-01', 0, 0, '18', 90);
INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (4, 'Whatever works', '2015-23-01', 2, 0, 'PG-15', 120);
INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (5, 'Back to the future', '1985-23-01', 4, 0, 'U', 90);
INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (6, 'Up', '2010-23-01', 1, 1, 'U', 90);
INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (7, 'Mad Max', '2017-23-01', 0, 1, '18', 140);
INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (8, 'Fast & Furious', '2002-23-01', 0, 0, '18', 120);
INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (9, 'Mighty Aphrodite', '1997-23-01', 2, 0, '18', 114);
INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (10, 'Finding Dory', '2018-23-01', 1, 1, 'U', 100);
INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (11, 'Star Wars', '1975-23-01', 4, 0, '12A', 120);
INSERT INTO Movie (id, name, release_date, genre, tresde, rating, duration) VALUES (12, 'Toy Story 5', '2025-23-01', 4, 0, '12A', 120);

--
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (1, 1);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (1, 3);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (1, 5);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (1, 6);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (2, 1);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (2, 2);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (2, 4);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (3, 2);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (3, 4);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (3, 6);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (4, 3);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (4, 5);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (4, 6);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (5, 1);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (5, 2);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (5, 4);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (6, 3);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (6, 5);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (6, 6);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (7, 1);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (7, 2);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (7, 4);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (8, 3);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (8, 4);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (8, 6);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (9, 1);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (9, 2);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (9, 5);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (10, 3);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (10, 4);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (10, 6);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (11, 1);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (11, 2);
INSERT INTO cinema_movie (movie_id, cinema_id) VALUES (11, 5);

INSERT INTO Client (id, name, age,  email) VALUES (1, 'Armando', 28, 'armando@gmail.com');
INSERT INTO Client (id, name, age, email) VALUES (2, 'Blanca', 25, 'blanca@gmail.com');
INSERT INTO Client (id, name, age, email) VALUES (3, 'Manuel', 56, 'manuel@gmail.com');
INSERT INTO Client (id, name, age, email) VALUES (4, 'Elena', 33, 'elena@gmail.com');
INSERT INTO Client (id, name, age, email) VALUES (5, 'Teresa', 58, 'teresa@gmail.com');
INSERT INTO Client (id, name, age, email) VALUES (6, 'Lupe', 23, 'lupe@gmail.com');
INSERT INTO Client (id, name, age, email) VALUES (7, 'Jamie', 26, 'jamie@gmail.com');
INSERT INTO Client (id, name, age, email) VALUES (8, 'Carlos', 26, 'carlos@gmail.com');
INSERT INTO Client (id, name, age, email) VALUES (9, 'Mercedes', 59, 'mercedes@gmail.com');
INSERT INTO Client (id, name, age, email) VALUES (10, 'Juanki', 28, 'juanki@gmail.com');


INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (1, 1000, 29, 1, 1);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (2, 500, 60, 2, 2);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (3, 750, 15, 3, 3);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (4, 2000, 236, 4, 4);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (5, 855, 174, 5, 5);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (6, 1000, 58, 6, 6);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (7, 750, 295, 7, 7);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (8, 500, 37, 8, 8);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (9, 900, 69, 9, 9);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (10, 1000, 300, 10, 10);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (11, 500, 274, 11, 1);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (12, 500, 274, 1, 2);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (13, 850, 59, 2, 3);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (14, 1000, 137, 3, 4);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (15, 550, 203, 4, 5);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (16, 550, 203, 5, 6);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (17, 920, 123, 6, 7);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (18, 1000, 39, 7, 8);
INSERT INTO Ticket (id, price, seats, movie_id, client_id) VALUES (19, 1000, 29, 1, 1);

INSERT INTO Staff (id, name, salary, cinema_id) VALUES (1, 'Laura', 20000, 1);
INSERT INTO Staff (id, name, salary, cinema_id) VALUES (2, 'Pablo', 25000, 1);
INSERT INTO Staff (id, name, salary, cinema_id) VALUES (3, 'Vicky', 40000, 2);
INSERT INTO Staff (id, name, salary, cinema_id) VALUES (4, 'Esteban', 20000, 2);
INSERT INTO Staff (id, name, salary, cinema_id) VALUES (5, 'Lidia', 35000, 3);
INSERT INTO Staff (id, name, salary, cinema_id) VALUES (6, 'Jesus', 15000, 3);
INSERT INTO Staff (id, name, salary, cinema_id) VALUES (7, 'Lucia', 15000, 4);
INSERT INTO Staff (id, name, salary, cinema_id) VALUES (8, 'Paula', 25000, 4);
INSERT INTO Staff (id, name, salary, cinema_id) VALUES (9, 'Juan', 35000, 5);
INSERT INTO Staff (id, name, salary, cinema_id) VALUES (10, 'Sara', 24000, 5);
INSERT INTO Staff (id, name, salary, cinema_id) VALUES (11, 'Tomas', 37000, 6);
INSERT INTO Staff (id, name, salary, cinema_id) VALUES (12, 'Guille', 27000, 6);


