package db;

import java.util.List;

import domain.Customer;
import domain.product.Product;

public interface Shop {

	public List<Product> getProducts();

	public void addProduct(Product p);

	public void removeProduct(Product p);

	public Product getProduct(String id);

	public boolean hasProduct(Product p);

	public void addCustomer(Customer c);

	public void removeCustomer(Customer c);

	public boolean hasCustomer(Customer c);

	public List<Customer> getCustomers();

	public void saveData() throws Exception;

	public void loadData() throws Exception;

}
