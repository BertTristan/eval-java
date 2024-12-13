INSERT INTO entreprise (nom) VALUES ("Google"), ("Amazon"), ("ASUS");

INSERT INTO utilisateur (email, password) VALUES ("test.test@test.test", "toto");
INSERT INTO utilisateur (email, password, entreprise_id) VALUES ("toto.toto@toto.toto", "tutu", 1);

INSERT INTO convention (nom, subvention, salarie_maximum, entreprise_id) VALUES ("Première", 51.23, 10, 1);

INSERT INTO salarie (matricule, code_barre, convention_id) VALUES ("TEST", "TEST", 1);