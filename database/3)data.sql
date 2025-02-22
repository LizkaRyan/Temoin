insert into role(role) values('Membre simple'),
                             ('Admin');

insert into utilisateur(pseudo,email,password,id_role) values('Lizka','Lizka@gmail.com','itu16','ROL001'),
                                                             ('Ryan','Ryan@gmail.com','itu16','ROL002');

insert into modele(modele) values('Airbus A380'),
                                 ('Boeing 777'),
                                 ('Airbus A350'),
                                 ('Boeing 787 Dreamliner');

insert into avion(date_fabrication,avion,id_modele) values('2025-02-01','Yuero','MDL001'),
                                                    ('2024-01-01','Flight','MDL002');

insert into ville(ville) values('Paris'),
                               ('Madrid'),
                               ('Rome');

insert into type_siege(type_siege,prix_siege) values('Business',1000),
                                                    ('Economique',2500);

insert into avion_siege(id_avion,id_type_siege,nombre) values('AVN001','TPS001',10),
                                                             ('AVN001','TPS002',10),
                                                             ('AVN002','TPS001',15),
                                                             ('AVN002','TPS002',15);