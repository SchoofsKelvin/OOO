package domain.product;

public class Movie extends Product {

	public Movie(String id, String title) {
		super(id, title);
	}

	public Movie() {
	}

	@Override
	public double getPrice(int day) {
		int price = 5;
		int daysLeft = day - 3;
		if (day > 3) {
			price += daysLeft * 2;
		}
		return price;
	}

	@Override
	public String getType() {
		return "Movie";
	}
}
