package domain;

import javax.swing.JFrame;

import domain.db.Shop;
import domain.db.ShopFactory;

public class App {

	public void Start() throws Exception {
		String shopType = "file";
		// Add stuff to change shopType if needed
		Shop shop = new ShopFactory().createShop(shopType);
		shop.loadData();
		JFrame frame = new View(shop);
		frame.setSize(250, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
