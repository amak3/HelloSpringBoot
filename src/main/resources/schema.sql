CREATE TABLE IF NOT EXISTS Form (id INTEGER IDENTITY PRIMARY KEY,
									district varchar(255) UNIQUE,
									votes INTEGER);