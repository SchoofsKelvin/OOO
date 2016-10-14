package domain;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Subject {

	private ArrayList<Product>	products	= new ArrayList<>();
	private ArrayList<Customer>	customers	= new ArrayList<>();
	private ArrayList<Observer>	observers	= new ArrayList<>();

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		return (List<Product>) products.clone();
	}

	public void addProduct(Product p) {
		products.add(p);
		notifyObservers("New product: " + p.getType() + " " + p.getTitle());
	}

	public void removeProduct(Product p) {
		products.remove(p);
	}

	public Product getProduct(String id) {
		for (Product p : products) {
			if (p.getId().equalsIgnoreCase(id)) return p;
		}
		return null;
	}

	public boolean hasProduct(Product p) {
		return products.contains(p);
	}

	public void addCustomer(Customer c) {
		customers.add(c);
	}

	public void removeCustomer(Customer c) {
		customers.remove(c);
	}

	public boolean hasCustomer(Customer c) {
		return customers.contains(c);
	}

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

	public boolean isObserver(Customer c) {
		return observers.contains(c);
	}

}
