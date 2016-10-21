package domain.db;

public enum ShopType {
	FILE("ShopFromFile");

	private String className;

	ShopType(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return name().substring(0, 1).toUpperCase()
			+ name().substring(1).toLowerCase();
	};

	@SuppressWarnings("unchecked")
	public Class<? extends Shop> getClassObject() throws ClassNotFoundException {
		return (Class<? extends Shop>) Class
			.forName("domain.db." + className);
	}
}
