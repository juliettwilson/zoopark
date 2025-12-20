INSERT INTO enclosures (name, type) VALUES
                                        ('Savannah Zone', 'Open Area'),
                                        ('Big Cats Zone', 'Closed Area'),
                                        ('Herbivores Zone', 'Open Area');

INSERT INTO animals (animal_name, animal_species, animal_age, enclosure_id) VALUES
                                                                                ('Lion', 'Panthera leo', 5, 2),
                                                                                ('Tiger', 'Panthera tigris', 4, 2),
                                                                                ('Elephant', 'Loxodonta africana', 10, 1),
                                                                                ('Zebra', 'Equus quagga', 6, 3);

INSERT INTO keepers (keeper_name, expr_year) VALUES
                                                 ('John Smith', 7),
                                                 ('Alice Brown', 5),
                                                 ('Michael Green', 10);


INSERT INTO animals_keepers (animal_id, keeper_id) VALUES
                                                       (1, 1),
                                                       (1, 3),
                                                       (2, 2),
                                                       (3, 3),
                                                       (4, 1);