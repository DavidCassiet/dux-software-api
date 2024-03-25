-- Carga inicial de países
INSERT INTO Country (name, status)
VALUES ('España', true),
       ('Inglaterra', true),
       ('Italia', true),
       ('Alemania', true),
       ('Francia', true),
       ('Portugal', true),
       ('Países Bajos', true),
       ('Escocia', true),
       ('Turquía', true),
       ('Rusia', true);

-- Carga inicial de ligas
INSERT INTO League (name, status, country_id)
VALUES ('La Liga', true, (SELECT id FROM Country WHERE name = 'España')),
       ('Premier League', true, (SELECT id FROM Country WHERE name = 'Inglaterra')),
       ('Serie A', true, (SELECT id FROM Country WHERE name = 'Italia')),
       ('Bundesliga', true, (SELECT id FROM Country WHERE name = 'Alemania')),
       ('Ligue 1', true, (SELECT id FROM Country WHERE name = 'Francia')),
       ('Primeira Liga', true, (SELECT id FROM Country WHERE name = 'Portugal')),
       ('Eredivisie', true, (SELECT id FROM Country WHERE name = 'Países Bajos')),
       ('Süper Lig', true, (SELECT id FROM Country WHERE name = 'Turquía')),
       ('Premier League Rusa', true, (SELECT id FROM Country WHERE name = 'Rusia')),
       ('Scottish Premiership', true, (SELECT id FROM Country WHERE name = 'Escocia'));

-- Carga inicial de equipos
INSERT INTO Team (name, status, league_id)
VALUES ('Real Madrid', true, (SELECT id FROM League WHERE name = 'La Liga')),
       ('FC Barcelona', true, (SELECT id FROM League WHERE name = 'La Liga')),
       ('Manchester United', true, (SELECT id FROM League WHERE name = 'Premier League')),
       ('Liverpool FC', true, (SELECT id FROM League WHERE name = 'Premier League')),
       ('Juventus FC', true, (SELECT id FROM League WHERE name = 'Serie A')),
       ('AC Milan', true, (SELECT id FROM League WHERE name = 'Serie A')),
       ('Bayern Munich', true, (SELECT id FROM League WHERE name = 'Bundesliga')),
       ('Borussia Dortmund', true, (SELECT id FROM League WHERE name = 'Bundesliga')),
       ('Paris Saint-Germain', true, (SELECT id FROM League WHERE name = 'Ligue 1')),
       ('Olympique de Marseille', true, (SELECT id FROM League WHERE name = 'Ligue 1')),
       ('FC Porto', true, (SELECT id FROM League WHERE name = 'Primeira Liga')),
       ('Sporting CP', true, (SELECT id FROM League WHERE name = 'Primeira Liga')),
       ('Ajax Amsterdam', true, (SELECT id FROM League WHERE name = 'Eredivisie')),
       ('Feyenoord', true, (SELECT id FROM League WHERE name = 'Eredivisie')),
       ('Celtic FC', true, (SELECT id FROM League WHERE name = 'Scottish Premiership')),
       ('Rangers FC', true, (SELECT id FROM League WHERE name = 'Scottish Premiership')),
       ('Galatasaray SK', true, (SELECT id FROM League WHERE name = 'Süper Lig')),
       ('Fenerbahçe SK', true, (SELECT id FROM League WHERE name = 'Süper Lig')),
       ('FC Zenit Saint Petersburg', true, (SELECT id FROM League WHERE name = 'Premier League Rusa')),
       ('Spartak Moscow', true, (SELECT id FROM League WHERE name = 'Premier League Rusa')),
       ('SL Benfica', true, (SELECT id FROM League WHERE name = 'Primeira Liga')),
       ('Besiktas JK', true, (SELECT id FROM League WHERE name = 'Süper Lig')),
       ('SSC Napoli', true, (SELECT id FROM League WHERE name = 'Serie A')),
       ('Atlético Madrid', true, (SELECT id FROM League WHERE name = 'La Liga'));
