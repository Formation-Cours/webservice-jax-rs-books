package fr.webforce.entities;

public record BookEntity(
		String isbn,
		String titre,
		String auteurNom,
		String auteurPrenom,
		String editeur,
		String annee
		) {
}
