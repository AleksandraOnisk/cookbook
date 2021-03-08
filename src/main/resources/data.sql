INSERT INTO recipe (id, title, introduction, prep_time, number_of_servings, category, description, likes)
VALUES (1, 'Jajecznica', 'Przepyszne i zdrowe śniadanie', 15, 2, 'BREAKFAST',
        'Masło rozpuścić na patelni, dodać jajka i smażyć na wolnym ogniu do uzyskania ulubionej konsystencji. Posolić wg uznania',
        1),
       (2, 'Bruchetta', 'Włoskie tosty', 15, 4, 'LUNCH',
        'Pomidory sparzyć, obrać ze skórki, pokroić w kosteczkę, ułożyć na kromkach pieczywa, dodać ser i zapiekać w piekarniuki ok. 5 minut',
        2),
       (3, 'Kurczak z rożna', 'Szybki obiad', 60, 5, 'DINNER',
        'Kurczaka natrzeć mieszanka przyraw i piec w piekarniku ok 1h. ', 3),
       (4, 'Brownie z malinami', 'Bezglutenowy deser, idealny do kawy', 90, 24, 'DESSERT',
        'Gorzką czekoladę rozpuścić w maśle,dodać cukier, następnie ostudzić i dodać do mąki. Na wierzch ułozyć świeże maliny. Piec 30 minut w temp. 200st',
        10);

INSERT INTO ingredient (id, name, recipe_id)
VALUES (1, 'jajka', 1),
       (2, 'masło', 1),
       (3, 'kromka chleba', 1),
       (4, 'pomidor', 2),
       (5, 'ciabatta', 2),
       (6, 'świeża bazylia', 2),
       (7, 'mozzarella', 2),
       (8, 'kurczak, cały', 3),
       (9, 'mieszanka przypraw', 3),
       (10, 'świeże maliny', 4),
       (11, 'gorzka czekolada', 4),
       (12, 'mąka bezglutenowa', 4);

INSERT INTO user (first_name, last_name, password, email)
VALUES ('Aleksandra', 'Onisk', '{noop}Password1!', 'aleksandra.siciarz@gmail.com'),
       ('Karol', 'Wielki', '{noop}Password1!', 'karol@wp.pl'),
       ('Magdalena', 'Mała', '{noop}Password1!', 'magdalena@wp.pl');

INSERT INTO user_role (user_id, role)
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_USER');
