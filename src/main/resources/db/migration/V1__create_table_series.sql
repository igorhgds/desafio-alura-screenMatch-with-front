CREATE TABLE series (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255),
                        total_seasons INTEGER,
                        rating DOUBLE PRECISION,
                        category VARCHAR(50),
                        actors TEXT,
                        poster TEXT,
                        plot TEXT
);
