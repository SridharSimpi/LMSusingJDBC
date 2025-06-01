package lms;

import java.sql.*;
import java.time.LocalDate;

public class IssueDAO {
	Connection con = DBConnection.getConnection();

	public void issueBook(int userId, int bookID) throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT available_copies FROM books WHERE book_id = ?");
		ps.setInt(1, bookID);
		ResultSet rs = ps.executeQuery();

		if (rs.next() && rs.getInt("available_copies") > 0) {
			PreparedStatement ps1 = con
					.prepareStatement("INSERT INTO issued_books(user_id, book_id, issue_date) VALUES (?, ?, ?)");
			ps1.setInt(1, userId);
			ps1.setInt(2, bookID);
			ps1.setDate(3, Date.valueOf(LocalDate.now()));
			ps1.executeUpdate();

			PreparedStatement ps3 = con
					.prepareStatement("UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?");
			ps3.setInt(1, bookID);
			ps3.executeUpdate();

			System.out.println("Book Issued Successfully...");
		} else {
			System.out.println("Book not available.");
		}
	}

	public void returnBook(int bookId, int userId) throws SQLException {
		PreparedStatement ps = con.prepareStatement(
				"UPDATE issued_books SET return_date = ? WHERE user_id = ? AND book_id = ? AND return_date IS NULL");
		ps.setDate(1, Date.valueOf(LocalDate.now()));
		ps.setInt(2, userId);
		ps.setInt(3, bookId);
		int row = ps.executeUpdate();
		if (row > 0) {
			PreparedStatement ps1 = con
					.prepareStatement("UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?");
			ps1.setInt(1, bookId);
			ps1.executeUpdate();
			System.out.println("Book Returned successfully");
		} else {
			System.out.println("No issued book found for return.");
		}
	}

	public void viewIssuedBook() throws SQLException {
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM lmsjdbc.issued_books");
		while (rs.next()) {
			System.out.println("Issued ID: " + rs.getInt("issue_id") + "\nUser ID: " + rs.getInt("user_id")
					+ "\nBook ID: " + rs.getInt("book_id") + "\nIssued Date: " + rs.getDate("issue_date")
					+ "\nReturn Date: " + rs.getDate("return_date") + "\n");
		}
	}
}
