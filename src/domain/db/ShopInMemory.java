package domain.db;

import java.util.ArrayList;
import java.util.List;

import domain.Customer;
import domain.Observer;
import domain.Subject;
import domain.product.Product;

public class ShopInMemory implements Subject, Shop {

	protected ArrayList<Product>	products	= new ArrayList<>();
	protected ArrayList<Customer>	customers	= new ArrayList<>();
	protected ArrayList<Observer>	observers	= new ArrayList<>();

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
	public synchronized void saveData() throws Exception {}

	@Override
	public synchronized void loadData() throws Exception {}

}
