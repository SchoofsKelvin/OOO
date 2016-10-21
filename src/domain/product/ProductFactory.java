package domain.product;

import domain.state.AvailableState;
import domain.state.DamagedState;
import domain.state.RentedState;
import exception.DomainException;

public class ProductFactory {

	public Product createProduct(String type, String id, String title) {
		ProductType typ;
		try {
			typ = ProductType.valueOf(type.toUpperCase());
		} catch (Exception e) {
			throw new DomainException(
				"Could't find the product type '" + type + "'");
		}
		try {
			return typ.getClassObject().getConstructor(String.class, String.class)
				.newInstance(id, title);
		} catch (ClassNotFoundException e) {
			throw new DomainException(
				"Missing the Product class for type '" + type + "'");
		} catch (Exception e) {
			throw new DomainException(
				"Couldn't instantiate a class of the type '" + type + "'");
		}
	}

	public void loadState(Product prod, String state) {
		if (state == null) throw new DomainException("State is null");
		switch (state.toLowerCase()) {
			case "available":
				prod.setState(new AvailableState());
				break;
			case "damaged":
				prod.setState(new DamagedState());
				break;
			case "rented":
				prod.setState(new RentedState());
				break;
			default:
				throw new DomainException("Unknown state: " + state);
		}
	}
}
