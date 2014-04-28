package gui.main;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JButton;

public class WishListPanel extends JPanel {
	public WishListPanel(String user) {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JButton btnTest = new JButton("Test");
		springLayout.putConstraint(SpringLayout.NORTH, btnTest, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnTest, 10, SpringLayout.WEST, this);
		add(btnTest);
	}
}
