INSERT INTO enclosures (name, type) VALUES
                                        ('Теплая зона', 'Открытый участок'),
                                        ('Холодная зона', 'Закрытый участок'),
                                        ('Нейтральная зона', 'Открытый участок');

INSERT INTO animals (animal_name, animal_species, animal_age, enclosure_id) VALUES
                                                                                ('Арыстан', 'Panthera leo', 5, 2),
                                                                                ('Жолбарыс', 'Panthera tigris', 4, 2),
                                                                                ('Слон', 'Loxodonta africana', 10, 1),
                                                                                ('Зебра', 'Equus quagga', 6, 3);

INSERT INTO keepers (keeper_name, expr_year) VALUES
                                                 ('Смотритель Баглан', 7),
                                                 ('Смотритель Фарида', 5),
                                                 ('Смотритель Жандос', 10);


INSERT INTO animals_keepers (animal_id, keeper_id) VALUES
                                                       (1, 1),
                                                       (1, 3),
                                                       (2, 2),
                                                       (3, 3),
                                                       (4, 1);