insert into role(role) values('Membre simple'),
                             ('Admin');

insert into utilisateur(pseudo,email,password,id_role) values('Lizka','Lizka@gmail.com','itu16','ROL001'),
                                                             ('Ryan','Ryan@gmail.com','itu16','ROL002');

insert into modele(modele) values('Airbus A380'),
                                 ('Boeing 777'),
                                 ('Airbus A350'),
                                 ('Boeing 787 Dreamliner');

insert into avion(date_fabrication,id_modele) values('2025-02-01','MDL001'),
                                                    ('2024-01-01','MDL002');

insert into ville(ville) values('Paris'),
                               ('Madrid'),
                               ('Rome');