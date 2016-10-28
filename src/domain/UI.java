package domain;

import java.util.List;

import javax.swing.JOptionPane;

import domain.db.Shop;
import domain.db.ShopFactory;
import domain.db.ShopFromFile;
import domain.product.CD;
import domain.product.Game;
import domain.product.Movie;
import domain.product.Product;
import domain.product.ProductFactory;
import exception.DomainException;
import domain.product.ProductType;

public class UI {

	public static void loadData(Shop shop) {
		try {
			shop.loadData();
		} catch (Exception e) {
			error("Couldn't load the data: " + e.getMessage());
		}
	}

	public static void saveData(Shop shop) {
		try {
			shop.saveData();
		} catch (Exception e) {
			error("Couldn't save the data: " + e.getMessage());
		}
	}

	public static void addProduct(Shop shop) throws DomainException {
		Product product = null;
		ProductFactory productFactory = new ProductFactory();
		//String type = JOptionPane.showInputDialog("Enter the type (movie/game/cd)");
		String[] productType = new String [3];
		productType[0] = ProductType.CD.getClassName();
		productType[1] = ProductType.MOVIE.getClassName();
		productType[2] = ProductType.GAME.getClassName();
		String type = (String) JOptionPane.showInputDialog(null, "Choose now...",
		        "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, // Use
		        productType, // Array of choices
		        productType[1]); // Initial choice
		String id = JOptionPane.showInputDialog("Enter the id:");
		String title = JOptionPane.showInputDialog("Enter the title:");
		try {
			product = productFactory.createProduct(type,id, title);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		if (shop.hasProduct(product)) throw new DomainException("Already exist ");
		shop.addProduct(product);
	}

	public static void showProduct(Shop shop) throws DomainException {
		String id = JOptionPane.showInputDialog("Enter the id:");
		if (id == null || id.trim().isEmpty())
			throw new DomainException("Invalid ID");
		Product prod = shop.getProduct(id);
		if (prod == null)
			throw new DomainException("Product with the given ID not found");
		JOptionPane.showMessageDialog(null, "Found: " + prod.toString());
	}

	public static void showPrice(Shop shop) throws DomainException {
		String id = JOptionPane.showInputDialog("Enter the id:");
		if (id == null || id.trim().isEmpty())
			throw new DomainException("Invalid ID");
		Product prod = shop.getProduct(id);
		if (prod == null)
			throw new DomainException("Product with the given ID not found");
		String daysString = JOptionPane.showInputDialog("Enter the number of days:");
		int days = Integer.parseInt(daysString);
		JOptionPane.showMessageDialog(null, "Price: " + prod.getPrice(days));
	}

	public static void showAllProduct(Shop shop) throws DomainException {
		List<Product> lijst = shop.getProducts();
		String output = "";
		for (Product p : lijst) {
			output += p.toString() + "\n";
		}
		JOptionPane.showMessageDialog(null, "All product: \n" + output);
	}

	private static void error(String err) {
		JOptionPane.showMessageDialog(null, err, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void addCustomer(Shop shop) {
		// TODO Auto-generated method stub
	}

	public static void subscribe(Shop shop) {
		// TODO Auto-generated method stub
	}

	public static void unsubscribe(Shop shop) {
		// TODO Auto-generated method stub
	}

	public static void addObserver(Shop shop) {
		// TODO Auto-generated method stub

	}

	public static void deleteAllData(Shop shop) {
		// TODO Auto-generated method stub

	}

}
