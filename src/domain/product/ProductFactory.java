package domain.product;

import domain.state.ProductStateFactory;
import exception.DomainException;

public class ProductFactory {

	private ProductStateFactory stateFactory = new ProductStateFactory();

	public Product createProduct(String type, String id, String title) {
		ProductType typ;
		try {
			typ = ProductType.valueOf(type.toUpperCase());
		} catch (Exception e) {
			throw new DomainException("Could't find the product type '" + type + "'");
		}
		try {
			return typ.getClassObject().getConstructor(String.class, String.class)
				.newInstance(id, title);
		} catch (ClassNotFoundException e) {
			throw new DomainException("Missing the Product class for type '" + type + "'");
		} catch (Exception e) {
			throw new DomainException(
				"Couldn't instantiate a class of the type '" + type + "'");
		}
	}

}
