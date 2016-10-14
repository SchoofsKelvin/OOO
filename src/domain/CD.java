package domain;

public class CD extends Product {

	public CD(String id, String title) {
		super(id, title);
	}

	public CD() {
	}

	@Override
	public double getPrice(int day) {
		return day * 1.5;
	}

	@Override
	public String getType() {
		return "CD";
	}

}
