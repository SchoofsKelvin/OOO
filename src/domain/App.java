package domain;

import javax.swing.JFrame;

import domain.db.Shop;
import domain.db.ShopFromFile;

public class App {

	public void Start() throws Exception {
		Shop shop = new ShopFromFile();
		shop.loadData();
		JFrame frame = new View(shop);
		frame.setSize(250, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
