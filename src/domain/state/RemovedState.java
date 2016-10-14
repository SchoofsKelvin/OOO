package domain.state;

import domain.Product;

public class RemovedState extends ProductState {

	@Override
	public void rent(Product product) {
		error("This product is removed");
	}

	@Override
	public void bringBack(Product product, boolean damaged) {
		error("This product is removed");
	}

	@Override
	public void repair(Product product) {
		error("This product is removed");
	}

	@Override
	public void remove(Product product) {
		error("This product is already removed");
	}

	@Override
	public String getName() {
		return "Removed";
	}

}
