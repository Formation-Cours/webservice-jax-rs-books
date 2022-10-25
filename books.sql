--DROP DATABASE IF EXISTS bibliotheque;

CREATE DATABASE IF NOT EXISTS bibliotheque;

CREATE TABLE IF NOT EXISTS bibliotheque.livre (
    isbn VARCHAR(14) NOT NULL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    auteur_nom VARCHAR(150) NOT NULL,
    auteur_prenom VARCHAR(150),
    editeur VARCHAR(100),
    annee VARCHAR(4)
);

INSERT INTO bibliotheque.livre VALUES
    ("9782203001190", "Tintin au Tibet", "Hergé", "", "Casterman", "1993"),
    ("9782416007033", "Le livre de Java premier langage: Avec 109 exercices corrigés", "Tasso", "Anne", "eyrolles", "2022"),
    ("2203001178", "Tintin - L' Affaire Tournesol", "Hergé", "", "Casterman", "1993"),
    ("9782017168768", "Tintin - Les voitures de légende : Tintin et les autos européennes", "Hergé","", "Hachette Comics", "2022"),
    ("2203136804", "Tintin au Tibet", "Hergé", "", "Casterman", "2017"),
    ("9782203006485", "Tintin - Tome 16 Petit format : Objectif Lune", "Hergé", "", "Casterman", "2007");
