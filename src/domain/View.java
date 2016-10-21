package domain;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		addProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UI.addProduct(shop);
			}
		});
		showProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UI.showProduct(shop);
			}
		});

		showRentalPrice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UI.showPrice(shop);
			}
		});

		addCustomer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UI.addCustomer(shop);
			}
		});
		showCustomer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UI.addCustomer(shop);

			}
		});

		subcriberToNewsLetter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UI.addObserver(shop);
			}
		});
		unsubscribe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UI.addObserver(shop);
			}
		});
		saveAllData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UI.saveData(shop);
			}
		});
		deleteAllData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UI.deleteAllData(shop);
			}
		});
		showObserver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UI.addObserver(shop);
			}
		});

		/*
		 * Container container = getContentPane(); container.add(addProduct);
		 * container.add(showProduct); container.add(showRentalPrice);
		 * container.add(addCustomer); container.add(subcriberToNewsLetter);
		 * container.add(unsubscribe);
		 */

	}
}
