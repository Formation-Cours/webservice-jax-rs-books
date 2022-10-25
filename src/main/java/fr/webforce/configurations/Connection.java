package fr.webforce.configurations;

public class Connection {
	private static java.sql.Connection instance;

	public static java.sql.Connection getInstance() {
		if (instance == null) {
			instance = BDD.getConnection();
		}
		return instance;
	}
}
