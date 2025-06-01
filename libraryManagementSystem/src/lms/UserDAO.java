package lms;

import java.sql.*;

public class UserDAO {
	Connection con = DBConnection.getConnection();

	public void addUser(String name, String contact) throws SQLException {
		PreparedStatement ps = con.prepareStatement("INSERT INTO lmsjdbc.users(name, contact) VALUES(?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, name);
		ps.setString(2, contact);

		int i = ps.executeUpdate();
		if (i > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				System.out.println("User added with ID: " + rs.getInt(1));
			}
		} else {
			System.err.println("Failed to add user.");
		}
	}

	public void viewUsers() throws SQLException {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM lmsjdbc.users");
		while (rs.next()) {
			System.out.println("User ID: " + rs.getInt("user_id") + "\nName: " + rs.getString("name") + "\nContact: "
					+ rs.getString("contact") + "\n");
		}
	}
}
