package domain.state;

import domain.Product;

public abstract class ProductState {

	public abstract void rent(Product product);
	public abstract void bringBack(Product product,boolean damaged);
	public abstract void repair(Product product);
	public abstract void remove(Product product);
	public abstract String getName();
	
	public static void error(String message) {
		throw new IllegalStateException(message);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
