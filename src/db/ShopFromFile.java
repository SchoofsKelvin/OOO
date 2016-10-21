package db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Customer;
import domain.Observer;
import domain.Subject;
import domain.product.Product;
import domain.product.ProductFactory;
import domain.state.RemovedState;

public class ShopFromFile implements Subject, Shop {

	private ArrayList<Product>	products		= new ArrayList<>();
	private ArrayList<Customer>	customers		= new ArrayList<>();
	private ArrayList<Observer>	observers		= new ArrayList<>();
	private ProductFactory		productFactory	= new ProductFactory();

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		return (List<Product>) products.clone();
	}

	@Override
	public void addProduct(Product p) {
		products.add(p);
		notifyObservers("New product: " + p.getType() + " " + p.getTitle());
	}

	@Override
	public void removeProduct(Product p) {
		products.remove(p);
	}

	@Override
	public Product getProduct(String id) {
		for (Product p : products) {
			if (p.getId().equalsIgnoreCase(id)) return p;
		}
		return null;
	}

	@Override
	public boolean hasProduct(Product p) {
		return products.contains(p);
	}

	@Override
	public void addCustomer(Customer c) {
		customers.add(c);
	}

	@Override
	public void removeCustomer(Customer c) {
		customers.remove(c);
	}

	@Override
	public boolean hasCustomer(Customer c) {
		return customers.contains(c);
	}

	@Override
	public List<Customer> getCustomers() {
		return new ArrayList<>(customers);
	}

	@Override
	public void notifyObservers(String message) {
		for (Observer o : observers) {
			o.update(message);
		}
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public boolean hasObserver(Observer o) {
		return observers.contains(o);
	}

	@Override
	public synchronized void saveData()
		throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("products.txt");
		for (Product p : getProducts()) {
			if ( !(p.getState() instanceof RemovedState)) {
				writer.println(p.getType() + ";" + p.getId() + ";" + p.getTitle()
					+ ";" + p.getState().getName());
			}
		}
		writer.close();
		writer = new PrintWriter("customers.txt");
		for (Customer c : getCustomers()) {
			writer.println(c.getFirstName() + ";" + c.getLastName() + ";"
				+ c.getEmail() + ";" + (hasObserver(c) ? "true" : "false"));
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

	@Override
	public synchronized void loadData() throws Exception {
		products.clear();
		customers.clear();
		observers.clear();
		File file = new File("products.txt");
		if ( !file.exists()) return;
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] data = split(line);
				if (data.length < 4) throw new IllegalArgumentException(
					"Unknown saved product: " + line);
				Product prod =
					productFactory.createProduct(data[0], data[1], data[2]);
				productFactory.loadState(prod, data[3]);
				addProduct(prod);
			}
		} catch (FileNotFoundException e) {
			throw e;
		}
		file = new File("customers.txt");
		if ( !file.exists()) return;
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String[] data = split(scanner.nextLine());
				Customer c = new Customer(data[2], data[0], data[1]);
				addCustomer(c);
				if (data[3].equals("true")) {
					addObserver(c);
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		}
	}

}
