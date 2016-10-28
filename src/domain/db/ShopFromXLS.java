package domain.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Customer;
import domain.product.Product;
import domain.product.ProductFactory;
import domain.state.ProductStateFactory;
import domain.state.RemovedState;

public class ShopFromXLS extends ShopInMemory {

	private ProductFactory		productFactory	= new ProductFactory();
	private ProductStateFactory	stateFactory	= new ProductStateFactory();
	private ExcelHandler		excelHandler	= new ExcelHandler();

	@Override
	public synchronized void saveData() throws Exception {
		ArrayList<ArrayList<String>> data = new ArrayList<>();
		for (Product p : getProducts()) {
			if ( !(p.getState() instanceof RemovedState)) {
				ArrayList<String> row = new ArrayList<>(4);
				row.add(p.getType());
				row.add(p.getId());
				row.add(p.getTitle());
				row.add(p.getState().getName());
				data.add(row);
			}
		}
		excelHandler.write(new File("products.xls"), data);
		for (Customer c : getCustomers()) {
			ArrayList<String> row = new ArrayList<>(4);
			row.add(c.getFirstName());
			row.add(c.getLastName());
			row.add(c.getEmail());
			row.add(hasObserver(c) ? "true" : "false");
			data.add(row);
		}
		excelHandler.write(new File("customers.xls"), data);
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
				if (data.length < 4)
					throw new IllegalArgumentException("Unknown saved product: " + line);
				Product prod = productFactory.createProduct(data[0], data[1], data[2]);
				prod.setState(stateFactory.createState(data[3]));
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
