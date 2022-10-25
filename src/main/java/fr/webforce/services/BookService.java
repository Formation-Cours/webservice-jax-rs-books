package fr.webforce.services;

import fr.webforce.entities.BookEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookService {
	private final Connection conn = fr.webforce.configurations.Connection.getInstance();


	public Optional<Integer> insert(BookEntity book) {
		try (PreparedStatement st = conn.prepareStatement("INSERT INTO livre VALUES (?,?,?,?,?,?)")) {
			st.setString(1, book.isbn());
			st.setString(2, book.titre());
			st.setString(3, book.auteurNom());
			st.setString(4, book.auteurPrenom());
			st.setString(5, book.editeur());
			st.setString(6, book.annee());
			return Optional.of(st.executeUpdate());
		} catch (SQLException e) {
			return Optional.empty();
		}
	}

	public List<BookEntity> findAll() {
		try (Statement st = conn.createStatement()) {
			ResultSet rs = st.executeQuery("SELECT * FROM livre");
			List<BookEntity> books = new ArrayList<>();
			while (rs.next()) {
				BookEntity book = new BookEntity(
						rs.getString("isbn"),
						rs.getString("titre"),
						rs.getString("auteur_nom"),
						rs.getString("auteur_prenom"),
						rs.getString("editeur"),
						rs.getString("annee")
				);
				books.add(book);
			}

			return books;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Optional<BookEntity> findByID(String isbn) {
		try (PreparedStatement st = conn.prepareStatement("SELECT * FROM livre WHERE isbn = ?")) {
			st.setString(1, isbn);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return Optional.of(new BookEntity(
						rs.getString("isbn"),
						rs.getString("titre"),
						rs.getString("auteur_nom"),
						rs.getString("auteur_prenom"),
						rs.getString("editeur"),
						rs.getString("annee")
				));
			}
			return Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Optional<Integer> delete(String isbn) {
		try (PreparedStatement st = conn.prepareStatement("DELETE FROM livre WHERE isbn = ?")) {
			st.setString(1, isbn);
			int nb = st.executeUpdate();
			if (nb > 0) {
				return Optional.of(nb);
			}
			return Optional.empty();

		} catch (SQLException e) {
			return Optional.of(-1);
		}
	}
}
