package domain;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domain.db.Shop;
import domain.db.ShopFactory;
import domain.db.ShopType;

public class App {

	public static void main(String[] args) throws Exception {
		int typ = JOptionPane.showOptionDialog(null, "ShopType?", "ShopType Chooser", 0, 0,
			null, ShopType.values(), null);
		ShopType shopType = ShopType.values()[typ];
		Shop shop = new ShopFactory().createShop(shopType);
		System.out.println(shop);
		shop.loadData();
		JFrame frame = new View(shop);
		frame.setSize(250, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
