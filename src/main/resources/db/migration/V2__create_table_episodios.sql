CREATE TABLE episodios (
                           id SERIAL PRIMARY KEY,
                           season INTEGER,
                           title VARCHAR(255),
                           episode_number INTEGER,
                           rating DOUBLE PRECISION,
                           release_date DATE,
                           serie_id INTEGER NOT NULL,
                           CONSTRAINT fk_serie
                               FOREIGN KEY (serie_id)
                                   REFERENCES series(id)
                                   ON DELETE CASCADE
);
