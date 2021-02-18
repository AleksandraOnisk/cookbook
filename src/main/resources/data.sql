INSERT INTO recipes (title,introduction, prep_time, number_of_servings, category, ingredients, description, favourite)
VALUES
('KURCZAK PO TOSKAŃSKU',
 'Filety z kurczaka w kremowym sosie ze szpinakiem i suszonymi pomidorami.',
 60, 4,'DINNER',
 '650 g filetów z kurczaka, 2 ząbki czosnku, 1 łyżeczka suszonego oregano, 200 ml bulionu drobiowego, 200 ml śmietanki 18% do zup i sosów , ok. 10 kawałków suszonych pomidorów, 125 g szpinaku,' ||
 'sól, pieprz, oliwa, mąka pszenna, tarty parmezan lub grana padano','Całe mięso doprawić solą, pieprzem, oregano. Smażyć aż kurczak ładnie się zrumieni.Wlać śmietankę i zagotować. Posypać szpinakiem.', true),
('JAJKO SADZONE Z HUMMUSEM I AWOKADO',
'Zdrowe i szybkie śniadanie!',
20, 2,'BREAKFAST',
'6 łyżek HUMMUSU, 2 kromki dobrego chleba' ||
'sól, pieprz, oliwa, mąka pszenna, tarty parmezan lub grana padano','Podawać z opieczoną w tosterze kromką chleba.', false),
('SAŁATKA OBŁĘDNA', 'Z kuskusem, granatem i winogronami', 20, 2,'LUNCH', 'MIODOWY SOS WINEGRET, 1/2 szklanki kuskusu, 4 łyżki orzeszków pinii, 1/2 granatu ', 'Rukolę opłukać, osuszyć, odciąć twarde łodyżki i włożyć do salaterki z kuskusem.
Wymieszać ze startym lub pokruszonym serem kozim.', true)
;