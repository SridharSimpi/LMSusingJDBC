package lms;

import java.sql.SQLException;
import java.util.Scanner;

public class LibraryApp {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		UserDAO user = new UserDAO();
		IssueDAO issue = new IssueDAO();
		BookDao book = new BookDao();

		while (true) {
			System.out.println("------LIBRARY MANAGEMENT SYSTEM-------");
			System.out.println("1. Add user");
			System.out.println("2. View Users");
			System.out.println("3. Add book");
			System.out.println("4. View books");
			System.out.println("5. Issue book");
			System.out.println("6. Return book");
			System.out.println("7. View Issued books");
			System.out.println("8. Exit");
			System.out.print("Choose option: ");

			int choice = scan.nextInt();
			scan.nextLine(); 

			switch (choice) {
			case 1:
				System.out.print("Enter Name: ");
				String name = scan.nextLine();

				System.out.print("Enter Contact: ");
				String contact = scan.nextLine();

				user.addUser(name, contact);
				break;
			case 2:
				user.viewUsers();
				break;
			case 3:
				System.out.print("Enter Book ID: ");
				int book_id = scan.nextInt();
				scan.nextLine();

				System.out.print("Enter Title: ");
				String title = scan.nextLine();

				System.out.print("Enter Author: ");
				String author = scan.nextLine();

				System.out.print("Enter Category: ");
				String category = scan.nextLine();

				System.out.print("Enter Total Copies: ");
				int totalCopies = scan.nextInt();

				System.out.print("Enter Available Copies: ");
				int availableCopies = scan.nextInt();

				book.addBook(book_id, title, author, category, totalCopies, availableCopies);
				break;
			case 4:
				book.viewBooks();
				break;
			case 5:
				System.out.print("Enter User ID: ");
				int userId = scan.nextInt();

				System.out.print("Enter Book ID: ");
				int bookId = scan.nextInt();

				issue.issueBook(userId, bookId);
				break;
			case 6:
				System.out.print("Enter Book ID: ");
				int bId = scan.nextInt();

				System.out.print("Enter User ID: ");
				int uId = scan.nextInt();

				issue.returnBook (uId,bId);
				break;
			case 7:
				issue.viewIssuedBook();
				break;
			case 8:
				System.out.println("Exiting...");
				scan.close();
				System.exit(0);
			default:
				System.out.println("Invalid Option.");
			}
		}
	}
}
