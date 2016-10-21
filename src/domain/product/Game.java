package domain.product;

public class Game extends Product {

	public Game(String id, String title) {
		super(id, title);
	}

	public Game() {
	}

	@Override
	public double getPrice(int day) {
		return day * 3;
	}

	@Override
	public String getType() {
		return "Game";
	}

}
