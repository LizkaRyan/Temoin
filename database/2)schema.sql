CREATE TABLE modele
(
    id_modele VARCHAR(50) default 'MDL00' || nextval('seq_modele'),
    modele    VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_modele),
    UNIQUE (modele)
);

CREATE TABLE ville
(
    id_ville VARCHAR(50) default 'VLL00' || nextval('seq_ville'),
    ville    VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_ville),
    UNIQUE (ville)
);

CREATE TABLE type_siege
(
    id_type_siege      VARCHAR(50) default 'TPS00' || nextval('seq_type_siege'),
    type_siege         VARCHAR(50)   NOT NULL,
    prix_siege         VARCHAR(50)   NOT NULL,
    nb_siege_promotion INTEGER       NOT NULL,
    promotion          NUMERIC(5, 2) NOT NULL,
    PRIMARY KEY (id_type_siege),
    UNIQUE (type_siege)
);

CREATE TABLE role
(
    id_role VARCHAR(50) default 'ROL00' || nextval('seq_role'),
    role    VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_role),
    UNIQUE (role)
);

CREATE TABLE parametre_reservation
(
    id_parametre_reservation VARCHAR(50) default 'PRS00' || nextval('seq_parametre_reservation'),
    heure_avant_vol          INTEGER NOT NULL,
    date_changement          DATE    NOT NULL,
    PRIMARY KEY (id_parametre_reservation)
);

CREATE TABLE parametre_annulation
(
    id_parametre_annulation VARCHAR(50) default 'PRA00' || nextval('seq_parametre_annulation'),
    heure_avant_vol         INTEGER NOT NULL,
    date_changement         DATE    NOT NULL,
    PRIMARY KEY (id_parametre_annulation)
);

CREATE TABLE tranche_age
(
    id_tranche_age VARCHAR(100) default 'TRA00' || nextval('seq_tranche_age'),
    tranche_age    VARCHAR(50),
    PRIMARY KEY (id_tranche_age)
);

CREATE TABLE avion
(
    id_avion         VARCHAR(50) default 'AVN00' || nextval('seq_avion'),
    avion            VARCHAR(50) NOT NULL,
    date_fabrication DATE        NOT NULL,
    id_modele        VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_avion),
    UNIQUE (avion),
    FOREIGN KEY (id_modele) REFERENCES modele (id_modele)
);

CREATE TABLE vol
(
    id_vol      VARCHAR(50) default 'VOL00' || nextval('seq_vol'),
    date_vol    TIMESTAMP      NOT NULL,
    prix_vol    NUMERIC(15, 2) NOT NULL,
    id_avion    VARCHAR(50)    NOT NULL,
    destination VARCHAR(50)    NOT NULL,
    PRIMARY KEY (id_vol),
    FOREIGN KEY (id_avion) REFERENCES avion (id_avion),
    FOREIGN KEY (destination) REFERENCES ville (id_ville)
);

CREATE TABLE utilisateur
(
    id_utilisateur VARCHAR(50) default 'USR00' || nextval('seq_utilisateur'),
    email          VARCHAR(50)  NOT NULL,
    pseudo         VARCHAR(50)  NOT NULL,
    password       VARCHAR(255) NOT NULL,
    id_role        VARCHAR(50)  NOT NULL,
    PRIMARY KEY (id_utilisateur),
    UNIQUE (email),
    FOREIGN KEY (id_role) REFERENCES role (id_role)
);

CREATE TABLE tranche_age_promotion
(
    id_tranche_age_promotion VARCHAR(100) default 'TRA00' || nextval('seq_tranche_age_promotion'),
    age_min                  INTEGER        NOT NULL,
    age_max                  INTEGER        NOT NULL,
    pourcentage              NUMERIC(15, 2) NOT NULL,
    date_update              TIMESTAMP,
    id_tranche_age           VARCHAR(100)   NOT NULL,
    PRIMARY KEY (id_tranche_age_promotion),
    FOREIGN KEY (id_tranche_age) REFERENCES tranche_age (id_tranche_age)
);

CREATE TABLE reservation
(
    id_reservation   VARCHAR(50) default 'RSV00' || nextval('seq_reservation'),
    date_reservation TIMESTAMP      NOT NULL,
    prix_reservation NUMERIC(15, 2) NOT NULL,
    nb_passager      INTEGER        NOT NULL,
    id_type_siege    VARCHAR(50)    NOT NULL,
    id_utilisateur   VARCHAR(50)    NOT NULL,
    id_vol           VARCHAR(50)    NOT NULL,
    PRIMARY KEY (id_reservation),
    FOREIGN KEY (id_type_siege) REFERENCES type_siege (id_type_siege),
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur (id_utilisateur),
    FOREIGN KEY (id_vol) REFERENCES vol (id_vol)
);

CREATE TABLE annulation_reservation
(
    id_annulation_reservation VARCHAR(50) default 'ANR00' || nextval('seq_annulation_reservation'),
    date_annulation           TIMESTAMP   NOT NULL,
    id_reservation            VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_annulation_reservation),
    UNIQUE (id_reservation),
    FOREIGN KEY (id_reservation) REFERENCES reservation (id_reservation)
);

CREATE TABLE passeport
(
    id_passeport     VARCHAR(50) default 'PSP00' || nextval('seq_passeport'),
    src_photo        VARCHAR(255),
    prix_reservation NUMERIC(15, 2) NOT NULL,
    id_tranche_age   VARCHAR(100)   NOT NULL,
    id_reservation   VARCHAR(50)    NOT NULL,
    PRIMARY KEY (id_passeport),
    FOREIGN KEY (id_tranche_age) REFERENCES tranche_age (id_tranche_age),
    FOREIGN KEY (id_reservation) REFERENCES reservation (id_reservation)
);

CREATE TABLE avion_siege
(
    id_avion      VARCHAR(50),
    id_type_siege VARCHAR(50),
    nombre        INTEGER NOT NULL,
    PRIMARY KEY (id_avion, id_type_siege),
    FOREIGN KEY (id_avion) REFERENCES avion (id_avion),
    FOREIGN KEY (id_type_siege) REFERENCES type_siege (id_type_siege)
);
