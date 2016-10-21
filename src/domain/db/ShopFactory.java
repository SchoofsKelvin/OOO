package domain.db;

import exception.DomainException;

public class ShopFactory {

	public Shop createShop(String type) {
		ShopType typ;
		try {
			typ = ShopType.valueOf(type.toUpperCase());
		} catch (Exception e) {
			throw new DomainException("Could't find the shop type '" + type + "'");
		}
		try {
			return typ.getClassObject().newInstance();
		} catch (ClassNotFoundException e) {
			throw new DomainException(
				"Missing the Shop class for type '" + type + "'");
		} catch (Exception e) {
			throw new DomainException(
				"Couldn't instantiate a class of the type '" + type + "'");
		}
	}
}
