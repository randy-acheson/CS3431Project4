package gui.main;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import controllers.ViewEventController;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainCDFrame extends JFrame {
	private SpringLayout springLayout;
	int windowWidth = 800;
	private JLabel lblCdWarehouse;
	private JButton btnCatalog;
	private JButton btnRecommendations;
	private JButton btnWishList;
	private JLabel lblWelcomeUser;
	
	CDSelectionPanel cdSelectionPanel;
	RecommendationsPanel recommendationsPanel;
	WishListPanel wishListPanel;

	public MainCDFrame(final String user) {

		setSize(windowWidth, 600);

		setTitle("CD Warehouse");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 

		springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		ViewEventController.getInstance().setMainCDFrame(this);
		
		makeConstraints(user);
		
		cdSelectionPanel = new CDSelectionPanel(user);
		getContentPane().add(cdSelectionPanel);
		btnCatalog.setEnabled(false);

		recommendationsPanel = new RecommendationsPanel(user);
		getContentPane().add(recommendationsPanel);
		
		wishListPanel = new WishListPanel(user);
		getContentPane().add(wishListPanel);
		
		btnCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cdSelectionPanel.setVisible(true);
				wishListPanel.setVisible(false);
				recommendationsPanel.setVisible(false);
				
				btnCatalog.setEnabled(false);
				btnRecommendations.setEnabled(true);
				btnWishList.setEnabled(true);
			}
		});   
		
		btnRecommendations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cdSelectionPanel.setVisible(false);
				wishListPanel.setVisible(false);
				recommendationsPanel.setVisible(true);
				
				btnCatalog.setEnabled(true);
				btnRecommendations.setEnabled(false);
				btnWishList.setEnabled(true);
			}
		});   
		
		btnWishList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cdSelectionPanel.setVisible(false);
				wishListPanel.setVisible(true);
				recommendationsPanel.setVisible(false);
				
				btnCatalog.setEnabled(true);
				btnRecommendations.setEnabled(true);
				btnWishList.setEnabled(false);
			}
		});   
		
		}

	protected void makeConstraints(String user) {
		String welcomeUser = new String ("Welcome " + user + "!");
		
		lblCdWarehouse = new JLabel("CD Warehouse");
		btnCatalog = new JButton("Catalog");
		btnRecommendations = new JButton("Recommendations");
		btnWishList = new JButton("Wish List");
		lblWelcomeUser = new JLabel(welcomeUser);
		springLayout.putConstraint(SpringLayout.EAST, lblWelcomeUser, 203, SpringLayout.WEST, getContentPane());

		springLayout.putConstraint(SpringLayout.NORTH, lblCdWarehouse, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCdWarehouse, 275, SpringLayout.WEST, getContentPane());
		lblCdWarehouse.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		springLayout.putConstraint(SpringLayout.NORTH, btnCatalog, 70, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnCatalog, 45, SpringLayout.WEST, getContentPane());

		springLayout.putConstraint(SpringLayout.NORTH, btnWishList, 0, SpringLayout.NORTH, btnCatalog);
		springLayout.putConstraint(SpringLayout.WEST, btnWishList, -250, SpringLayout.WEST, btnRecommendations);

		springLayout.putConstraint(SpringLayout.NORTH, btnRecommendations, 0, SpringLayout.NORTH, btnCatalog);
		springLayout.putConstraint(SpringLayout.EAST, btnRecommendations, -45, SpringLayout.EAST, getContentPane());

		springLayout.putConstraint(SpringLayout.NORTH, lblWelcomeUser, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblWelcomeUser, 0, SpringLayout.WEST, getContentPane());
		lblWelcomeUser.setForeground(Color.BLUE);
		lblWelcomeUser.setFont(new Font("Tahoma", Font.ITALIC, 14));

		getContentPane().add(lblCdWarehouse);
		getContentPane().add(btnCatalog);
		getContentPane().add(btnRecommendations);
		getContentPane().add(btnWishList);
		getContentPane().add(lblWelcomeUser);
		
		if (user.equals(" ")) {
			btnCatalog.setVisible(false);
			btnRecommendations.setVisible(false);
			btnWishList.setVisible(false);
			lblWelcomeUser.setVisible(false);
		}
	}
	
}
