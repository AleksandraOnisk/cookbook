INSERT INTO recipe (id, title, introduction, prep_time, number_of_servings, category, description, likes)
VALUES (1, 'Jajecznica', 'Jajecznicę możesz spożywać w każdej formie i praktycznie z każdym możliwym dodatkiem!', 15, 2,
        'BREAKFAST',
        ',Masło rozpuścić na patelni, dodać jajka i smażyć na wolnym ogniu do uzyskania ulubionej konsystencji. Posolić wg uznania',
        1),
       (2, 'Bruchetta', 'Włoskie tosty, smak Sycylii!', 15, 4, 'LUNCH',
        'Pomidory sparzyć, obrać ze skórki, pokroić w kosteczkę, ułożyć na kromkach pieczywa, dodać ser i zapiekać w piekarniku ok. 5 minut',
        2),
       (3, 'Kurczak z rożna', 'Szybki obiad!', 60, 5, 'DINNER',
        'Kurczaka natrzeć mieszanka przyraw i piec w piekarniku ok 1h. ', 3),
       (4, 'Brownie z malinami', 'Bezglutenowy deser, idealny do kawy!', 90, 24, 'DESSERT',
        'Gorzką czekoladę rozpuścić w maśle,dodać cukier, następnie ostudzić i dodać do mąki. Na wierzch ułozyć świeże maliny. Piec 30 minut w temp. 200st',
        10),
       (5, 'Twarożek', 'Uwielbiam! Sekretem jest dobry, bardzo tłusty twaróg i tłusta śmietana!', 15, 6, 'BREAKFAST',
        'Twaróg przetrzyj lub rozdrobnij widelcem. Dodaj śmietanę, pokrojoną w kostkę rzodkiewkę i szczypiorek. Posól i dokładnie wymieszaj. Voilà! ',
        1),
       (6, 'Frankfuterki', 'Najszybsze śniadanie, kiedy nie mam pomysłu i czasu na przygotowania!', 10, 1, 'BREAKFAST',
        'Ich przygotowaniu nie towarzyszy też żadna większa filozofia – najlepsze są gotowane z dodatkiem odrobiny ketchupu i musztardy.',
        11),
       (7, 'Sałatka Grecka - Horiatiki', 'Oryginalna grecka sałatka z serem feta i świeżymi warzywami!', 15, 2, 'LUNCH',
        'Warzywa przełóż na talerze, posyp oliwkami, na wierzch połóż plaster sera feta (możesz również rozgnieść ser palcami, by lepiej wymieszał się z sosem). Polej sporą ilością oliwy i posyp oregano, świeżo mielonym pieprzem i solą',
        15),
       (8, 'Ekspresowy Shake', 'Ekspresowa i zdrowa kolacja!', 5, 2, 'SUPPER',
        'Szpinak łączymy z bananem w kielichu blendera. Dodajemy kakao i nasiona chia. Całość zalewamy napojem migdałowym. Miksujemy i przelewamy do szklanek.',
        3),
       (9, 'Jogurt naturalny z borówkami', 'Ciesz się smakiem kolacji w nieco lżejszej odsłonie!', 5, 1, 'SUPPER',
        'Jogurt naturalny połączyć z borówkami amerykańskimi, posypać płatkami migdałowymi!', 3);

INSERT INTO ingredient (id, name, recipe_id)
VALUES (1, 'jajka', 1),
       (2, 'masło', 1),
       (3, 'kromka chleba', 1),
       (4, 'pomidory', 2),
       (5, 'ciabatta', 2),
       (6, 'świeża bazylia', 2),
       (7, 'mozzarella', 2),
       (8, 'kurczak, cały', 3),
       (9, 'mieszanka przypraw', 3),
       (10, 'świeże maliny', 4),
       (11, 'gorzka czekolada', 4),
       (12, 'mąka bezglutenowa', 4),
       (13, 'twaróg tłusty', 5),
       (14, 'szczypiorek', 5),
       (15, 'śmietana 18%', 5),
       (16, 'frankfuterki', 6),
       (17, 'ser feta', 7),
       (18, 'oliwki', 7),
       (19, 'oliwa z oliwek', 7),
       (20, 'banan', 8),
       (21, 'szpinak baby', 7),
       (22, 'mleko migdałowe', 7),
       (23, 'kakao', 8),
       (24, 'nasiona chia', 8),
       (25, 'jogurt naturalny', 9),
       (26, 'borówki amerykańskie', 9),
       (27, 'płatki migdałowe', 9);

INSERT INTO user (id, first_name, last_name, password, email)
VALUES (1, 'Aleksandra', 'Onisk', '{noop}Password1!', 'aleksandra.siciarz@gmail.com'),
       (2, 'Karol', 'Wielki', '{noop}Password1!', 'karol@wp.pl'),
       (3, 'Anna', 'Mucha', '{noop}xxx', 'xxx@wp.pl'),
       (4, 'Magdalena', 'Mała', '{noop}Password1!', 'magdalena@wp.pl'),
       (5, 'Admin', 'Tylko', '{noop}admin', 'admin@wp.pl');

INSERT INTO user_role (user_id, role)
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER'),
       (2, 'ROLE_ADMIN'),
       (3, 'ROLE_USER'),
       (4, 'ROLE_USER'),
       (5, 'ROLE_ADMIN');
