package domain.state;

import exception.DomainException;

public class ProductStateFactory {

	public ProductState createState(String type) {
		ProductStateType typ;
		try {
			typ = ProductStateType.valueOf(type.toUpperCase());
		} catch (Exception e) {
			throw new DomainException(
				"Could't find the productstate type '" + type + "'");
		}
		try {
			return typ.getClassObject().newInstance();
		} catch (ClassNotFoundException e) {
			throw new DomainException(
				"Missing the ProductState class for type '" + type + "'");
		} catch (Exception e) {
			throw new DomainException(
				"Couldn't instantiate a class of the type '" + type + "'");
		}
	}

}
