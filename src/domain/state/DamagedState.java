package domain.state;

import domain.product.Product;

public class DamagedState extends ProductState {

	@Override
	public void rent(Product product) {
		error("This product is damaged");
	}

	@Override
	public void bringBack(Product product, boolean damaged) {
		error("This product is damaged");
	}

	@Override
	public void repair(Product product) {
		product.setState(new AvailableState());
	}

	@Override
	public void remove(Product product) {
		product.setState(new RemovedState());
	}

	@Override
	public String getName() {
		return "Damaged";
	}

}
