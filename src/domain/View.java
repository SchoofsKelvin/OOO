package domain;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import db.Shop;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public View(Shop shop){
		JPanel options = new JPanel();
		JButton addProduct = new JButton("Add product");
		JButton showProduct = new JButton("Show product");
		JButton showProducts = new JButton("Show products");
		JButton showPrice = new JButton("Show rental price");
		JButton addCustomer = new JButton("Add customer");
		JButton subscribe = new JButton("Subscribe to newsletter");
		JButton unsubscribe = new JButton("Unsubscribe");
		
		options.add(addProduct);
		options.add(showProduct);
		options.add(showProducts);
		options.add(showPrice);
		options.add(addCustomer);
		options.add(subscribe);
		options.add(unsubscribe);
		
		setContentPane(options);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(7, 1));
		pack();
		this.setSize(new Dimension(500, 500));
		
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
		showProducts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UI.showAllProduct(shop);
				
			}
		});
		showPrice.addActionListener(new ActionListener() {
			
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
		subscribe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UI.subscribe(shop);				
			}
		});
		unsubscribe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UI.unsubscribe(shop);
			}
		});
		
	}
}
