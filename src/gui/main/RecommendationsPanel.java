package gui.main;

import gui.login.User;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.CDController;
import controllers.ViewEventController;

public class RecommendationsPanel extends JPanel {
	
	SpringLayout springLayout;
	int rCD;
	int numElements = 0;
	List<Integer> recommendedCDIndexes = new ArrayList<Integer>();
	List<CD> cds = new ArrayList<CD>();
	List<User> users = new ArrayList<User>();
	DefaultListModel<String> listRecommendationsModel;
	JList<String> listRecommendations;
	JScrollPane listScrollerRecommendations;
	
	JLabel lblGenreText;
	JLabel lblArtistText;
	JLabel lblAlbumText;
	private JButton btnCheckOut;
	private JButton btnAddToWish;

	public RecommendationsPanel(String user) {
		springLayout = new SpringLayout();
		this.setLayout(springLayout);
		this.setPreferredSize(new Dimension(780, 525));
		
		cds = CDController.getInstance().getCDModel().getCDCollection();

		listRecommendationsModel = new DefaultListModel<String>();
		listRecommendations = new JList<String>(listRecommendationsModel);
		listRecommendations.setCellRenderer(new DefaultListCellRenderer());
		listScrollerRecommendations = new JScrollPane(listRecommendations);

		listRecommendations.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listRecommendations.setLayoutOrientation(JList.VERTICAL);
		listRecommendations.setVisibleRowCount(-1);
		
		while (numElements < 3) {
			rCD = (int)(Math.random() * (cds.size()));
			if (!(recommendedCDIndexes.contains(rCD))) {
				recommendedCDIndexes.add(rCD);
				listRecommendationsModel.addElement(cds.get(rCD).getAlbum());
				numElements++;
			}
		}
		
		JLabel lblHereAreYour = new JLabel("Here are your recommendations.");
		springLayout.putConstraint(SpringLayout.NORTH, lblHereAreYour, 150, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblHereAreYour, 243, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, lblHereAreYour, 10, SpringLayout.WEST, this);
		lblHereAreYour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblHereAreYour);
		
		springLayout.putConstraint(SpringLayout.NORTH, listScrollerRecommendations, 6, SpringLayout.SOUTH, lblHereAreYour);
		springLayout.putConstraint(SpringLayout.WEST, listScrollerRecommendations, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, listScrollerRecommendations, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, listScrollerRecommendations, 312, SpringLayout.WEST, this);
		add(listScrollerRecommendations);
		
		JLabel lblGenre = new JLabel("Genre:");
		springLayout.putConstraint(SpringLayout.NORTH, lblGenre, 0, SpringLayout.NORTH, listScrollerRecommendations);
		springLayout.putConstraint(SpringLayout.WEST, lblGenre, 13, SpringLayout.EAST, listScrollerRecommendations);
		add(lblGenre);

		JLabel lblArtist = new JLabel("Artist:");
		springLayout.putConstraint(SpringLayout.NORTH, lblArtist, 10, SpringLayout.SOUTH, lblGenre);
		springLayout.putConstraint(SpringLayout.WEST, lblArtist, 13, SpringLayout.EAST, listScrollerRecommendations);
		add(lblArtist);

		JLabel lblAlbum = new JLabel("Album:");
		springLayout.putConstraint(SpringLayout.NORTH, lblAlbum, 10, SpringLayout.SOUTH, lblArtist);
		springLayout.putConstraint(SpringLayout.WEST, lblAlbum, 13, SpringLayout.EAST, listScrollerRecommendations);
		add(lblAlbum);
		
		lblGenreText = new JLabel(" ");
		springLayout.putConstraint(SpringLayout.WEST, lblGenreText, 6, SpringLayout.EAST, lblGenre);
		springLayout.putConstraint(SpringLayout.SOUTH, lblGenreText, 0, SpringLayout.SOUTH, lblGenre);
		add(lblGenreText);
		
		lblArtistText = new JLabel(" ");
		springLayout.putConstraint(SpringLayout.WEST, lblArtistText, 0, SpringLayout.WEST, lblGenreText);
		springLayout.putConstraint(SpringLayout.NORTH, lblArtistText, 0, SpringLayout.NORTH, lblArtist);
		add(lblArtistText);
		
		lblAlbumText = new JLabel(" ");
		springLayout.putConstraint(SpringLayout.WEST, lblAlbumText, 0, SpringLayout.WEST, lblGenreText);
		springLayout.putConstraint(SpringLayout.NORTH, lblAlbumText, 0, SpringLayout.NORTH, lblAlbum);
		add(lblAlbumText);
		
		btnCheckOut = new JButton("(Check Out)");

		springLayout.putConstraint(SpringLayout.SOUTH, btnCheckOut, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnCheckOut, 0, SpringLayout.EAST, this);
		btnCheckOut.setEnabled(false);
		add(btnCheckOut);
		
		btnAddToWish = new JButton("Add to Wish List");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddToWish, 0, SpringLayout.NORTH, btnCheckOut);
		springLayout.putConstraint(SpringLayout.EAST, btnAddToWish, -6, SpringLayout.WEST, btnCheckOut);
		btnAddToWish.setEnabled(false);
		add(btnAddToWish);

		
		listRecommendations.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				CD cd = findCD(listRecommendations.getSelectedValue().toString());
				btnAddToWish.setEnabled(false);
				lblGenreText.setText(cd.getGenre());
				lblArtistText.setText(cd.getArtist());
				lblAlbumText.setText(cd.getAlbum());
				btnCheckOut.setEnabled(true);
				if (!ViewEventController.getInstance().isInWishList(listRecommendations.getSelectedValue())) {
					btnAddToWish.setEnabled(true);
				}
			}
		});
		
		btnAddToWish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ViewEventController.getInstance().getWishListPanel().addCD(listRecommendations.getSelectedValue());
            	btnAddToWish.setEnabled(false);
            }
		});
	}

	protected CD findCD(String cdName) {
		for (CD cd : cds) {
			if (cdName.equals(cd.getAlbum())) {
				return cd;
			}
		}
		return null;
	}
}
