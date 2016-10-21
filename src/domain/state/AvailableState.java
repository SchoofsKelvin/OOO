package domain.state;

import domain.product.Product;

public class AvailableState extends ProductState {

	@Override
	public void rent(Product product) {
		product.setState(new RentedState());
	}

	@Override
	public void bringBack(Product product, boolean damaged) {
		error("This product isn't rented");
	}

	@Override
	public void repair(Product product) {
		error("This product isn't damaged");
	}

	@Override
	public void remove(Product product) {
		product.setState(new RemovedState());
	}

	@Override
	public String getName() {
		return "Available";
	}

}
