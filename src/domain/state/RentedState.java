package domain.state;

import domain.Product;

public class RentedState extends ProductState {

	@Override
	public void rent(Product product) {
		error("This product is already rented");
	}

	@Override
	public void bringBack(Product product, boolean damaged) {
		product.setState(damaged ? new DamagedState() : new AvailableState());
	}

	@Override
	public void repair(Product product) {
		error("This product is rented");
	}

	@Override
	public void remove(Product product) {
		error("This product is rented");
	}

	@Override
	public String getName() {
		return "Rented";
	}

}
