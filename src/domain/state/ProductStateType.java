package domain.state;

public enum ProductStateType {
	AVAILABLE, DAMAGED, REMOVED, RENTED;

	public String properCase() {
		return name().substring(0, 1).toUpperCase()
			+ name().substring(1).toLowerCase();
	};

	@Override
	public String toString() {
		return properCase();
	};

	@SuppressWarnings("unchecked")
	public Class<? extends ProductState> getClassObject()
		throws ClassNotFoundException {
		return (Class<? extends ProductState>) Class
			.forName("domain.state." + properCase() + "State");
	}
}
