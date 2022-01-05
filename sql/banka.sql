DROP SCHEMA IF EXISTS banka;
CREATE SCHEMA banka DEFAULT CHARACTER SET utf8;
USE banka;

CREATE TABLE racuni (
id INT AUTO_INCREMENT,
sifra VARCHAR (2) UNIQUE NOT NULL,
vlasnik VARCHAR (10) NOT NULL,
stanje DECIMAL (10.2) NOT NULL,
raspolozivo_stanje DECIMAL (10.2) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE nalozi (
id INT AUTO_INCREMENT,
vreme_kreiranja TIMESTAMP NOT NULL,
iznos DECIMAL (10.2) NOT NULL,
vreme_realizacije TIMESTAMP,
uplatilac_id INT NOT NULL,
primalac_id INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (uplatilac_id) REFERENCES racuni(id),
FOREIGN KEY (primalac_id) REFERENCES racuni(id)
);

INSERT INTO racuni (sifra, vlasnik, stanje, raspolozivo_stanje) VALUES ('1a', 'a', 200.00, 0.00);
INSERT INTO racuni (sifra, vlasnik, stanje, raspolozivo_stanje) VALUES ('2b', 'b', 300.00, 0.00);
INSERT INTO racuni (sifra, vlasnik, stanje, raspolozivo_stanje) VALUES ('3c', 'c', 0.00, 500.00);
INSERT INTO racuni (sifra, vlasnik, stanje, raspolozivo_stanje) VALUES ('4d', 'd', 150.00, 150.00);
INSERT INTO racuni (sifra, vlasnik, stanje, raspolozivo_stanje) VALUES ('5e', 'e', 0.00, 0.00);

INSERT INTO nalozi (vreme_kreiranja, iznos, vreme_realizacije, uplatilac_id, primalac_id) VALUES ('2017-09-01 01:00:00', 100.00, '2017-09-01 23:59:00', 1, 2);
INSERT INTO nalozi (vreme_kreiranja, iznos, vreme_realizacije, uplatilac_id, primalac_id) VALUES ('2017-09-02 02:00:00', 200.00, NULL, 1, 3);
INSERT INTO nalozi (vreme_kreiranja, iznos, vreme_realizacije, uplatilac_id, primalac_id) VALUES ('2017-09-03 03:00:00', 300.00, NULL, 2, 3);