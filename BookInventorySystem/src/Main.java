import java.util.Scanner;
import java.io.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Scanner bookTitle= new Scanner(System.in);
		Scanner bookNewTitle= new Scanner(System.in);
		Scanner bookAuthor= new Scanner(System.in);
		Scanner bookPrice= new Scanner(System.in);
		Scanner bookQuant= new Scanner(System.in);
		BookInventorySystem books = new BookInventorySystem();
		boolean exit=false; 
		
		while(!exit) {
			System.out.println("-----Welcome to the Book Inventory System-----");
            System.out.println("1. Add a book");
            System.out.println("2. Remove a book");
            System.out.println("3. Update a book");
            System.out.println("4. Search for a book by title");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
           
            switch(choice) {
            case 1:
            	System.out.print("Enter Title: ");
            	 String title= bookTitle.nextLine();
            	 System.out.print("Enter Author: ");
            	 String author= bookAuthor.nextLine();
            	 System.out.print("Enter Price: ");
            	 double price= bookPrice.nextDouble();
            	 System.out.print("Enter Quantity: ");
            	 int quantity= bookQuant.nextInt();
            	books.addBook(new Book(title, author, price, quantity));
            	break;
            case 2:
            	System.out.print("Enter Title: ");
           	 	title= bookTitle.nextLine();
           	 	books.removeBook(title);
            	break;
            case 3:
            	System.out.print("Enter Title of Book You would like to Update: ");
           	 	title= bookTitle.nextLine();
           	 	System.out.print("Enter Updated Title: ");
           	 	String newTitle= bookNewTitle.nextLine();
           	 	System.out.print("Enter Updated Author: ");
           	 	author= bookAuthor.nextLine();
           	 	System.out.print("Enter Updated Price: ");
           	 	price= bookPrice.nextDouble();
           	 	System.out.print("Enter Updated Quantity: ");
           	 	quantity= bookQuant.nextInt();
           	 	
				Book newBook= new Book(newTitle, author, price, quantity);
           	 	books.updateBook(newTitle, newBook);
           	 	
            	break;
            case 4:
            	System.out.print("Enter Title of Book You would like to Search: ");
           	 	title= bookTitle.nextLine();
           	 	books.searchBook(title);
            	break;
            case 5:
            	exit=true;
            	System.out.println("Exiting Program...");
            	break;
            default:
            	System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            		
            }
		}

	}


}
