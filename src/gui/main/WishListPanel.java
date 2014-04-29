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

public class WishListPanel extends JPanel {
	SpringLayout springLayout;
	List<CD> cds = new ArrayList<CD>();
	List<User> users = new ArrayList<User>();
	List<String> wishListCDs = new ArrayList<String>();
	DefaultListModel<String> listWishListModel;
	JList<String> listWishList;
	JScrollPane listScrollerWishList;

	JLabel lblGenreText;
	JLabel lblArtistText;
	JLabel lblAlbumText;
	private JButton btnCheckOut;
	private JButton btnRemoveFromWish;

	public WishListPanel(String user) {
		springLayout = new SpringLayout();
		this.setLayout(springLayout);
		this.setPreferredSize(new Dimension(780, 525));

		ViewEventController.getInstance().setWishListPanel(this);
		cds = CDController.getInstance().getCDModel().getCDCollection();

		listWishListModel = new DefaultListModel<String>();
		listWishList = new JList<String>(listWishListModel);
		listWishList.setCellRenderer(new DefaultListCellRenderer());
		listScrollerWishList = new JScrollPane(listWishList);

		listWishList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listWishList.setLayoutOrientation(JList.VERTICAL);
		listWishList.setVisibleRowCount(-1);


		JLabel lblHereAreYour = new JLabel("Here is your wish list.");
		springLayout.putConstraint(SpringLayout.NORTH, lblHereAreYour, 150, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblHereAreYour, 243, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, lblHereAreYour, 10, SpringLayout.WEST, this);
		lblHereAreYour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblHereAreYour);

		springLayout.putConstraint(SpringLayout.NORTH, listScrollerWishList, 6, SpringLayout.SOUTH, lblHereAreYour);
		springLayout.putConstraint(SpringLayout.WEST, listScrollerWishList, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, listScrollerWishList, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, listScrollerWishList, 312, SpringLayout.WEST, this);
		add(listScrollerWishList);

		JLabel lblGenre = new JLabel("Genre:");
		springLayout.putConstraint(SpringLayout.NORTH, lblGenre, 0, SpringLayout.NORTH, listScrollerWishList);
		springLayout.putConstraint(SpringLayout.WEST, lblGenre, 13, SpringLayout.EAST, listScrollerWishList);
		add(lblGenre);

		JLabel lblArtist = new JLabel("Artist:");
		springLayout.putConstraint(SpringLayout.NORTH, lblArtist, 10, SpringLayout.SOUTH, lblGenre);
		springLayout.putConstraint(SpringLayout.WEST, lblArtist, 13, SpringLayout.EAST, listScrollerWishList);
		add(lblArtist);

		JLabel lblAlbum = new JLabel("Album:");
		springLayout.putConstraint(SpringLayout.NORTH, lblAlbum, 10, SpringLayout.SOUTH, lblArtist);
		springLayout.putConstraint(SpringLayout.WEST, lblAlbum, 13, SpringLayout.EAST, listScrollerWishList);
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

		btnRemoveFromWish = new JButton("Remove from Wish List");
		springLayout.putConstraint(SpringLayout.NORTH, btnRemoveFromWish, 0, SpringLayout.NORTH, btnCheckOut);
		springLayout.putConstraint(SpringLayout.EAST, btnRemoveFromWish, -6, SpringLayout.WEST, btnCheckOut);
		btnRemoveFromWish.setEnabled(false);
		add(btnRemoveFromWish);


		listWishList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (listWishList.getSelectedValue() != null) {
					CD cd = findCD(listWishList.getSelectedValue());
					lblGenreText.setText(cd.getGenre());
					lblArtistText.setText(cd.getArtist());
					lblAlbumText.setText(cd.getAlbum());
					btnCheckOut.setEnabled(true);
					btnRemoveFromWish.setEnabled(true);
				}
				else {
					lblGenreText.setText(" ");
					lblArtistText.setText(" ");
					lblAlbumText.setText(" ");
					btnCheckOut.setEnabled(false);
					btnRemoveFromWish.setEnabled(false);
				}
			}
		});

		btnRemoveFromWish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCD(listWishList.getSelectedValue());
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

	public void addCD(String album) {
		wishListCDs = ViewEventController.getInstance().getWishListCDs();
		if (!wishListCDs.contains(album)){
			wishListCDs.add(album);
			listWishListModel.addElement(album);
		}
	}

	public void removeCD(String album) {
		wishListCDs.remove(album);
		listWishListModel.removeElement(album);
	}
}
