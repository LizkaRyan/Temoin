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

insert into type_siege(type_siege,prix_siege,nb_siege_promotion,promotion) values('Business',1000,5,10),
                                                    ('Economique',2500,5,10);

insert into avion_siege(id_avion,id_type_siege,nombre) values('AVN001','TPS001',10),
                                                             ('AVN001','TPS002',10),
                                                             ('AVN002','TPS001',15),
                                                             ('AVN002','TPS002',15);

insert into parametre_reservation(heure_avant_vol,date_changement) values(2,'2025-01-01');
insert into parametre_annulation(heure_avant_vol,date_changement) values(1,'2025-01-01');

insert into tranche_age(tranche_age) values
                                         ('Enfant'),
                                         ('Jeune'),
                                         ('Adulte');

insert into tranche_age_promotion(age_min,age_max,pourcentage,date_update,id_tranche_age) values    (0,10,10,'2025-01-01','TRA001'),
                                                                                                    (10,18,25,'2025-01-01','TRA002'),
                                                                                                    (19,100,30,'2025-01-01','TRA003');