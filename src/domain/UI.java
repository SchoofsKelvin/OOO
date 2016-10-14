package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import domain.state.AvailableState;
import domain.state.DamagedState;
import domain.state.RemovedState;
import domain.state.RentedState;
import exception.DomainException;

public class UI {

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			Customer cus = new Customer("a@b.com", "Alpha", "Beta");
			CD cd = new CD("cd", "Le CD");
			Game game = new Game("game", "Le Game");
			Movie movie = new Movie("movie", "Le Movie");
			Shop shop = new Shop();
			shop.addCustomer(cus);
			shop.addProduct(cd);
			shop.addProduct(game);
			shop.addProduct(movie);
			saveData(shop);
			shop = new Shop();
			loadData(shop);
			for (Customer customer : shop.getCustomers()) {
				System.out.println(customer);
			}
			for (Product product : shop.getProducts()) {
				System.out.println(product);
			}
			return;
		}
		Shop shop = new Shop();
		loadData(shop);
		String menu = "1. Add product\n2. Show product\n3. Show rental price\n"
			+ "4. Show all product\n5. Save data \n\n0. Quit";
		int choice = -1;
		while (true) {
			try {
				String choiceString = JOptionPane.showInputDialog(menu);
				choice = Integer.parseInt(choiceString);
				if (choice == 1) {
					addProduct(shop);
				} else if (choice == 2) {
					showProduct(shop);
				} else if (choice == 3) {
					showPrice(shop);
				} else if (choice == 4) {
					showAllProduct(shop);
				} else if (choice == 5) {
					saveData(shop);
				} else if (choice == 0) {
					System.exit(0);
				} else
					throw new DomainException("Invalid choice");
			} catch (NumberFormatException e) {
				error("Invalid number");
			} catch (DomainException e) {
				error(e.getMessage());
			} catch (Exception e) {
				error("Unknown exception: " + e.getMessage());
			}
		}
	}

	private static void addProduct(Shop shop) throws DomainException {
		Product product = null;
		String id = JOptionPane.showInputDialog("Enter the id:");
		String title = JOptionPane.showInputDialog("Enter the title:");
		String type = JOptionPane
			.showInputDialog("Enter the type (M for movie/G for game/C for CD):");
		if (type.equalsIgnoreCase("C") || type.equalsIgnoreCase("CD")) {
			product = new CD(id, title);
		} else if (type.equalsIgnoreCase("G") || type.equalsIgnoreCase("Game")) {
			product = new Game(id, title);
		} else if (type.equalsIgnoreCase("M") || type.equalsIgnoreCase("Movie")) {
			product = new Movie(id, title);
		} else {
			error("type is niet juist gegeven");
		}
		if (shop.hasProduct(product)) throw new DomainException("Already exist ");
		shop.addProduct(product);
	}

	public static void showProduct(Shop shop) throws DomainException {
		String id = JOptionPane.showInputDialog("Enter the id:");
		if (id == null || id.trim().isEmpty()) throw new DomainException("Invalid ID");
		Product prod = shop.getProduct(id);
		if (prod == null)
			throw new DomainException("Product with the given ID not found");
		JOptionPane.showMessageDialog(null, "Found: " + prod.toString());
	}

	private static void showPrice(Shop shop) throws DomainException {
		String id = JOptionPane.showInputDialog("Enter the id:");
		if (id == null || id.trim().isEmpty()) throw new DomainException("Invalid ID");
		Product prod = shop.getProduct(id);
		if (prod == null)
			throw new DomainException("Product with the given ID not found");
		String daysString = JOptionPane.showInputDialog("Enter the number of days:");
		int days = Integer.parseInt(daysString);
		JOptionPane.showMessageDialog(null, "Price: " + prod.getPrice(days));
	}

	private static void showAllProduct(Shop shop) throws DomainException {
		List<Product> lijst = shop.getProducts();
		String output = "";
		for (Product p : lijst) {
			output += p.toString() + "\n";
		}
		JOptionPane.showMessageDialog(null, "All product: \n" + output);
	}

	private static void saveData(Shop shop)
		throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("products.txt");
		for (Product p : shop.getProducts()) {
			if ( !(p.getState() instanceof RemovedState)) {
				writer.println(p.getType() + ";" + p.getId() + ";" + p.getTitle() + ";"
					+ p.getState().getName());
			}
		}
		writer.close();
		writer = new PrintWriter("customers.txt");
		for (Customer c : shop.getCustomers()) {
			writer.println(c.getFirstName() + ";" + c.getLastName() + ";" + c.getEmail()
				+ ";" + (shop.isObserver(c) ? "true" : "false"));
		}
		writer.close();
	}

	private static String[] split(String str) {
		Matcher mat = Pattern.compile("[^;]+").matcher(str);
		ArrayList<String> res = new ArrayList<>();
		while (mat.find()) {
			res.add(mat.group());
		}
		return res.toArray(new String[res.size()]);
	}

	private static void loadData(Shop shop) throws DomainException {
		File file = new File("products.txt");
		if ( !file.exists()) return;
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] data = split(line);
				if (data.length < 4)
					throw new IllegalArgumentException("Unknown saved product: " + line);
				Product prod;
				switch (data[0].toLowerCase()) {
					case "cd":
						prod = new CD(data[1], data[2]);
						break;
					case "game":
						prod = new Game(data[1], data[2]);
						break;
					case "movie":
						prod = new Movie(data[1], data[2]);
						break;
					default:
						throw new IllegalArgumentException(
							"Unknown saved product: " + line);
				}
				switch (data[3].toLowerCase()) {
					case "available":
						prod.setState(new AvailableState());
						break;
					case "damaged":
						prod.setState(new DamagedState());
						break;
					case "rented":
						prod.setState(new RentedState());
						break;
					default:
						throw new IllegalArgumentException(
							"Unknown state: " + data[3]);
				}
				shop.addProduct(prod);
			}
		} catch (FileNotFoundException e) {
			error("File Not Found");
		}
		file = new File("customers.txt");
		if ( !file.exists()) return;
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String[] data = split(scanner.nextLine());
				shop.addCustomer(new Customer(data[2], data[0], data[1]));
			}
		} catch (FileNotFoundException e) {
			error("File Not Found");
		}
	}

	private static void error(String err) {
		JOptionPane.showMessageDialog(null, err, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
