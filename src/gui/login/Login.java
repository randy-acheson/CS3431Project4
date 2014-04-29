package gui.login;

import gui.main.MainCDFrame;
import gui.main.CDSelectionPanel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;

import controllers.UserController;

import java.awt.Color;

public class Login extends JFrame {
	int windowWidth = 800;
	private JTextField txtLoginUsername;
	private JPasswordField txtLoginPassword;
	private JTextField txtRegisterUsername;
	private JPasswordField txtRegisterPassword;
	JLabel lblInvalidLogin;
	List<User> users;
	
	public Login() {
		
		users = UserController.getInstance().getUserModel().getUsers();

		setSize(windowWidth, 600);

		setTitle("CD Warehouse");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblCdWarehouse = new JLabel("CD Warehouse");
		springLayout.putConstraint(SpringLayout.NORTH, lblCdWarehouse, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCdWarehouse, 275, SpringLayout.WEST, getContentPane());
		lblCdWarehouse.setFont(new Font("Tahoma", Font.PLAIN, 36));
		getContentPane().add(lblCdWarehouse);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblLogin);
		
		JLabel lblRegister = new JLabel("Register");
		springLayout.putConstraint(SpringLayout.NORTH, lblLogin, 0, SpringLayout.NORTH, lblRegister);
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		springLayout.putConstraint(SpringLayout.NORTH, lblRegister, 140, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblRegister, -140, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblRegister);
		
		JLabel lblLoginUsername = new JLabel("Username");
		springLayout.putConstraint(SpringLayout.NORTH, lblLoginUsername, 171, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblLoginUsername, 90, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblLoginUsername);
		
		JLabel lblLoginPassword = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.NORTH, lblLoginPassword, 11, SpringLayout.SOUTH, lblLoginUsername);
		springLayout.putConstraint(SpringLayout.EAST, lblLoginPassword, 0, SpringLayout.EAST, lblLoginUsername);
		getContentPane().add(lblLoginPassword);
		
		txtLoginUsername = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtLoginUsername, 3, SpringLayout.SOUTH, lblLogin);
		springLayout.putConstraint(SpringLayout.WEST, lblLogin, 0, SpringLayout.WEST, txtLoginUsername);
		springLayout.putConstraint(SpringLayout.WEST, txtLoginUsername, 12, SpringLayout.EAST, lblLoginUsername);
		springLayout.putConstraint(SpringLayout.EAST, txtLoginUsername, 164, SpringLayout.EAST, lblLoginUsername);
		getContentPane().add(txtLoginUsername);
		txtLoginUsername.setColumns(10);
		
		txtLoginPassword = new JPasswordField();
		springLayout.putConstraint(SpringLayout.SOUTH, txtLoginPassword, 0, SpringLayout.SOUTH, lblLoginPassword);
		springLayout.putConstraint(SpringLayout.EAST, txtLoginPassword, 0, SpringLayout.EAST, txtLoginUsername);
		springLayout.putConstraint(SpringLayout.WEST, txtLoginPassword, 12, SpringLayout.EAST, lblLoginUsername);
		getContentPane().add(txtLoginPassword);
		txtLoginPassword.setColumns(10);
		
		txtRegisterUsername = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtRegisterUsername, 0, SpringLayout.WEST, lblRegister);
		springLayout.putConstraint(SpringLayout.SOUTH, txtRegisterUsername, 0, SpringLayout.SOUTH, lblLoginUsername);
		springLayout.putConstraint(SpringLayout.EAST, txtRegisterUsername, 152, SpringLayout.WEST, lblRegister);
		getContentPane().add(txtRegisterUsername);
		txtRegisterUsername.setColumns(10);
		
		JLabel lblRegisterUsername = new JLabel("Username");
		springLayout.putConstraint(SpringLayout.NORTH, lblRegisterUsername, 0, SpringLayout.NORTH, lblLoginUsername);
		springLayout.putConstraint(SpringLayout.EAST, lblRegisterUsername, -12, SpringLayout.WEST, txtRegisterUsername);
		getContentPane().add(lblRegisterUsername);
		
		txtRegisterPassword = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, txtRegisterPassword, 0, SpringLayout.NORTH, txtLoginPassword);
		springLayout.putConstraint(SpringLayout.WEST, txtRegisterPassword, 0, SpringLayout.WEST, lblRegister);
		springLayout.putConstraint(SpringLayout.EAST, txtRegisterPassword, 0, SpringLayout.EAST, txtRegisterUsername);
		getContentPane().add(txtRegisterPassword);
		txtRegisterPassword.setColumns(10);
		
		JLabel lblRegisterPassword = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.SOUTH, lblRegisterPassword, 0, SpringLayout.SOUTH, lblLoginPassword);
		springLayout.putConstraint(SpringLayout.EAST, lblRegisterPassword, 0, SpringLayout.EAST, lblRegisterUsername);
		getContentPane().add(lblRegisterPassword);
		
		JButton btnLogin = new JButton("Login");
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 6, SpringLayout.SOUTH, txtLoginPassword);
		springLayout.putConstraint(SpringLayout.EAST, btnLogin, 0, SpringLayout.EAST, txtLoginUsername);
		getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		springLayout.putConstraint(SpringLayout.NORTH, btnRegister, 6, SpringLayout.SOUTH, txtRegisterPassword);
		springLayout.putConstraint(SpringLayout.EAST, btnRegister, 0, SpringLayout.EAST, txtRegisterUsername);
		getContentPane().add(btnRegister);
		
		JButton btnLoginAsGuest = new JButton("Login as Guest");
		springLayout.putConstraint(SpringLayout.WEST, btnLoginAsGuest, 330, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnLoginAsGuest, -172, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(btnLoginAsGuest);
		
		lblInvalidLogin = new JLabel("Invalid Login");
		lblInvalidLogin.setForeground(Color.RED);
		lblInvalidLogin.setFont(new Font("Tahoma", Font.ITALIC, 11));
		springLayout.putConstraint(SpringLayout.NORTH, lblInvalidLogin, 6, SpringLayout.SOUTH, btnLogin);
		springLayout.putConstraint(SpringLayout.EAST, lblInvalidLogin, 0, SpringLayout.EAST, txtLoginUsername);
		getContentPane().add(lblInvalidLogin);
		lblInvalidLogin.setVisible(false);
		
		
		btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	for (User user : users) {
            		if (((txtLoginUsername.getText()).equals(user.getName())) && ((txtLoginPassword.getText()).equals(user.getPassword()))) {
            			MainCDFrame mainPage = new MainCDFrame(user.getName());
            			mainPage.setVisible(true);
            			closePanel();
            		}
            	}
            	lblInvalidLogin.setVisible(true);
            }
        });
		
		btnRegister.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	MainCDFrame mainPage = new MainCDFrame(txtRegisterUsername.getText());
            	mainPage.setVisible(true);
    			closePanel();
            }
        });
		
		
		btnLoginAsGuest.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	MainCDFrame mainPage = new MainCDFrame(" ");
            	mainPage.setVisible(true);
    			closePanel();
            }
        });   
		
	}

	protected void closePanel() {
		this.dispose();
	}
}
