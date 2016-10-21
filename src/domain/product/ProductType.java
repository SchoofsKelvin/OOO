package domain.product;

public enum ProductType {
	MOVIE("Movie"), GAME("Game"), CD("CD");

	private String className;

	ProductType(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return name().substring(0, 1).toUpperCase()
			+ name().substring(1).toLowerCase();
	};

	@SuppressWarnings("unchecked")
	public Class<? extends Product> getClassObject() throws ClassNotFoundException {
		return (Class<? extends Product>) Class
			.forName("domain.product." + className);
	}
}
