package domain.product;

import domain.state.AvailableState;
import domain.state.ProductState;
import exception.DomainException;

public abstract class Product {

	private String			id;
	private String			title;
	private ProductState	state = new AvailableState();

	public Product() {}

	public Product(String id, String title) {
		this();
		setId(id);
		setTitle(title);
	}

	private void setId(String id) {
		if (id == null || id.trim().isEmpty())
			throw new DomainException("Mag geen null en empty zijn");
		this.id = id.trim().toLowerCase();
	}

	private void setTitle(String title) {
		if (title == null || title.trim().isEmpty())
			throw new DomainException("Mag geen null en empty Zijn");
		this.title = title.trim().toLowerCase();
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public abstract double getPrice(int day);

	public abstract String getType();

	public void setState(ProductState currentState) {
		state = currentState;
	}

	public ProductState getState() {
		return state;
	}

	public void rent() {
		state.rent(this);
	}

	public void remove() {
		state.remove(this);
	}
	
	public void repair() {
		state.repair(this);
	}
	
	public void bringBack(boolean damaged) {
		state.bringBack(this, damaged);
	}

	@Override
	public String toString() {
		return getType() + "(" + getId() + "," + getTitle() + ") [" + state + "]";
	}

}
