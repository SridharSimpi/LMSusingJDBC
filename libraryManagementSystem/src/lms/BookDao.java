package lms;

import java.sql.*;

public class BookDao {
	Connection con = DBConnection.getConnection();

	public void addBook(int book_id, String title, String author, String category, int total_copies,
			int available_copies) throws SQLException {
		PreparedStatement ps = con.prepareStatement(
				"INSERT INTO lmsjdbc.books(book_id,title, author, category, total_copies, available_copies) VALUES (?, ?, ?, ?, ?, ?)");
		ps.setInt(1, book_id);
		ps.setString(2, title);
		ps.setString(3, author);
		ps.setString(4, category);
		ps.setInt(5, total_copies);
		ps.setInt(6, available_copies);

		int i = ps.executeUpdate();
		if (i > 0) {
			System.out.println("Book Added Successfully");
		} else {
			System.err.println("Failed to add book.");
		}
	}

	public void viewBooks() throws SQLException {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM lmsjdbc.books");
		while (rs.next()) {
			System.out.println("Book ID: " + rs.getInt("book_id") + "\nTitle: " + rs.getString("title") + "\nAuthor: "
					+ rs.getString("author") + "\nCategory: " + rs.getString("category") + "\nTotal Copies: "
					+ rs.getInt("total_copies") + "\nAvailable Copies: " + rs.getInt("available_copies") + "\n");
		}
	}
}
