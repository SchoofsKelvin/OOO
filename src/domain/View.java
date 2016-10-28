package domain;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.db.Shop;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	public View(Shop shop) {
		super("Shop");
		JPanel panel = new JPanel();
		JButton addProduct = new JButton("Add Product");
		JButton showProduct = new JButton("Show Product");
		JButton showRentalPrice = new JButton("Show Rental price");
		JButton addCustomer = new JButton("add Customer");
		JButton showCustomer = new JButton("ShowCustomer");
		JButton showObserver = new JButton("ShowObserver");
		JButton subcriberToNewsLetter = new JButton("Subcribe To Newsletter");
		JButton unsubscribe = new JButton("Unsubscribe");
		JButton saveAllData = new JButton("SaveAllData");
		JButton deleteAllData = new JButton("DeleteAllData");

		// set layout manager
		setContentPane(panel);
		setLayout(new GridLayout(10, 1));
		// add swing components to content pane
		panel.add(addProduct);
		panel.add(showProduct);
		panel.add(showRentalPrice);
		panel.add(addCustomer);
		panel.add(showCustomer);
		panel.add(subcriberToNewsLetter);
		panel.add(showObserver);
		panel.add(unsubscribe);
		panel.add(saveAllData);
		panel.add(deleteAllData);

		addProduct.addActionListener(e -> UI.addProduct(shop));
		showProduct.addActionListener(e -> UI.showProduct(shop));

		showRentalPrice.addActionListener(e -> UI.showPrice(shop));

		addCustomer.addActionListener(e -> UI.addCustomer(shop));
		showCustomer.addActionListener(e -> UI.addCustomer(shop));

		subcriberToNewsLetter.addActionListener(e -> UI.addObserver(shop));
		unsubscribe.addActionListener(e -> UI.addObserver(shop));
		saveAllData.addActionListener(e -> UI.saveData(shop));
		deleteAllData.addActionListener(e -> UI.deleteAllData(shop));
		showObserver.addActionListener(e -> UI.addObserver(shop));

		/*
		 * Container container = getContentPane(); container.add(addProduct);
		 * container.add(showProduct); container.add(showRentalPrice);
		 * container.add(addCustomer); container.add(subcriberToNewsLetter);
		 * container.add(unsubscribe);
		 */

	}
}
