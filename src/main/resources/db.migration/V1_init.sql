CREATE TABLE animals (
                         id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         animal_name VARCHAR(100),
                         animal_species VARCHAR(150),
                         animal_age INTEGER,
                         enclosure_id BIGINT,

                         CONSTRAINT fk_animals_enclosure
                             FOREIGN KEY (enclosure_id)
                                 REFERENCES enclosures (id)
);

CREATE TABLE keepers (
                         id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         keeper_name VARCHAR(100),
                         expr_year INTEGER
);

CREATE TABLE animals_keepers (
                                 animal_id BIGINT NOT NULL,
                                 keeper_id BIGINT NOT NULL,

                                 PRIMARY KEY (animal_id, keeper_id),

                                 CONSTRAINT fk_animals_keepers_animal
                                     FOREIGN KEY (animal_id)
                                         REFERENCES animals (id),

                                 CONSTRAINT fk_animals_keepers_keeper
                                     FOREIGN KEY (keeper_id)
                                         REFERENCES keepers (id)
);

