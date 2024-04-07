import java.io.*;
import java.util.ArrayList;

public class BookInventorySystem {
    private ArrayList<Book> inventory;

    public BookInventorySystem() {
        inventory = new ArrayList<>();
        loadInventory();
    }

    public void addBook(Book book) {
        inventory.add(book);
        System.out.println("Book Added!");
    }

    public void removeBook(String title) {
        for (Book book : inventory) {
            if (book.getTitle().equals(title)) {
                inventory.remove(book);
                System.out.println("Book Removed!");
                return;
            }
        }
        System.out.println("Book not found in inventory: " + title);
    }

    public void updateBook(String title, Book newBook) {
        for (Book book : inventory) {
            if (book.getTitle().equals(title)) {
                book.setTitle(newBook.getTitle());
                book.setAuthor(newBook.getAuthor());
                book.setPrice(newBook.getPrice());
                book.setQuantity(newBook.getQuantity());
                System.out.println("Book updated: " + book);
                return;
            }
        }
        System.out.println("Book not found in inventory: " + title);
    }

    public void searchBook(String title) {
        for (Book book : inventory) {
            if (book.getTitle().equals(title)) {
                System.out.println(book);
                return;
            }
        }
        System.out.println("Book not found in inventory: " + title);
    }

    private void loadInventory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0];
                String author = parts[1];
                double price = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                inventory.add(new Book(title, author, price, quantity));
            }
            System.out.println("Inventory loaded from file: inventory.txt");
        } catch (IOException e) {
            System.out.println("Error: Could not load inventory from file. Starting with empty inventory.");
        }
    }

    public ArrayList<Book> getInventory() {
        return inventory;
    }
    
}
