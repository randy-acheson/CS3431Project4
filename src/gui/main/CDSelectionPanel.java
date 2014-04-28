package gui.main;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import controllers.CDController;

import java.awt.Font;
import java.awt.font.TextAttribute;

public class CDSelectionPanel extends JPanel {
	List<CD> cds;
	int windowWidth = 800;
	int listWidth;
	SpringLayout springLayout;

	JList<String> listGenre;
	JList<String> listArtist;
	JList<String> listAlbum;
	JScrollPane listScrollerGenre;
	JScrollPane listScrollerArtist;
	JScrollPane listScrollerAlbum;
	DefaultListModel<String> listGenreModel;
	DefaultListModel<String> listArtistModel;
	DefaultListModel<String> listAlbumModel;

	private JButton btnCheckOut;
	private JLabel lblGenres;
	private JLabel lblArtists;
	private JLabel lblAlbums;

	public CDSelectionPanel(String user) {

		springLayout = new SpringLayout();
		this.setLayout(springLayout);
		this.setPreferredSize(new Dimension(780, 525));

		// Width of whole window minus white space between lists, divided by 3 lists
		listWidth = (int) Math.round((windowWidth - 70) / 3);

		cds = CDController.getInstance().getCDModel().getCDCollection();

		makeConstraints(user);

		setGenres();

		listGenre.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnCheckOut.setEnabled(false);
				listArtistModel.removeAllElements();
				setArtist(listGenre.getSelectedValue());
			}
		});

		listArtist.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnCheckOut.setEnabled(false);
				listAlbumModel.removeAllElements();
				setAlbum(listArtist.getSelectedValue());
			}
		});

		listAlbum.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					btnCheckOut.setEnabled(true);
				}
			}
		});

	}

	protected void setGenres() {
		for (CD cd : cds) {
			if (!(listGenreModel.contains(cd.getGenre()))) {
				listGenreModel.addElement(cd.getGenre());
			}
		}
	}

	protected void setArtist(String genre) {
		for (CD cd : cds) {
			if ((cd.getGenre()).equals(genre) && !(listArtistModel.contains(cd.getArtist()))) {
				listArtistModel.addElement(cd.getArtist());
			}
		}
	}


	protected void setAlbum(String artist) {
		for (CD cd : cds) {
			if ((cd.getArtist()).equals(artist) && !(listAlbumModel.contains(cd.getAlbum()))) {
				listAlbumModel.addElement(cd.getAlbum());
			}
		}
	}

	protected void makeConstraints(String user) {

		String welcomeUser = new String ("Welcome " + user + "!");

		btnCheckOut = new JButton("(Check Out)");		

		listGenreModel = new DefaultListModel<String>();
		listArtistModel = new DefaultListModel<String>();
		listAlbumModel = new DefaultListModel<String>();

		listGenre = new JList<String>(listGenreModel);
		listArtist = new JList<String>(listArtistModel);
		listAlbum = new JList<String>(listAlbumModel);

		listScrollerGenre = new JScrollPane(listGenre);
		listScrollerArtist = new JScrollPane(listArtist);
		listScrollerAlbum = new JScrollPane(listAlbum);

		lblGenres = new JLabel("Genres");
		lblArtists = new JLabel("Artists");
		lblAlbums = new JLabel("Albums");

		Font font = lblGenres.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

		listGenre.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listGenre.setLayoutOrientation(JList.VERTICAL);
		listGenre.setVisibleRowCount(-1);

		listArtist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listArtist.setLayoutOrientation(JList.VERTICAL);
		listArtist.setVisibleRowCount(-1);

		listAlbum.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listAlbum.setLayoutOrientation(JList.VERTICAL);
		listAlbum.setVisibleRowCount(-1);

		listScrollerGenre.setPreferredSize(new Dimension(listWidth, listWidth));
		listScrollerArtist.setPreferredSize(new Dimension(listWidth, listWidth));
		listScrollerAlbum.setPreferredSize(new Dimension(listWidth, listWidth));

		springLayout.putConstraint(SpringLayout.NORTH, listScrollerGenre, 150, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, listScrollerGenre, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, listScrollerGenre, -75, SpringLayout.SOUTH, this);

		springLayout.putConstraint(SpringLayout.NORTH, listScrollerArtist, 0, SpringLayout.NORTH, listScrollerGenre);
		springLayout.putConstraint(SpringLayout.WEST, listScrollerArtist, 10, SpringLayout.EAST, listScrollerGenre);
		springLayout.putConstraint(SpringLayout.SOUTH, listScrollerArtist, 0, SpringLayout.SOUTH, listScrollerGenre);
		springLayout.putConstraint(SpringLayout.EAST, listScrollerArtist, -10, SpringLayout.WEST, listScrollerAlbum);

		springLayout.putConstraint(SpringLayout.NORTH, listScrollerAlbum, 0, SpringLayout.NORTH, listScrollerGenre);
		springLayout.putConstraint(SpringLayout.SOUTH, listScrollerAlbum, -75, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, listScrollerAlbum, -10, SpringLayout.EAST, this);

		springLayout.putConstraint(SpringLayout.SOUTH, btnCheckOut, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnCheckOut, 0, SpringLayout.EAST, this);
		btnCheckOut.setEnabled(false);

		springLayout.putConstraint(SpringLayout.WEST, lblGenres, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblGenres, -6, SpringLayout.NORTH, listScrollerGenre);
		lblGenres.setFont(font.deriveFont(attributes));

		springLayout.putConstraint(SpringLayout.NORTH, lblArtists, 0, SpringLayout.NORTH, lblGenres);
		springLayout.putConstraint(SpringLayout.WEST, lblArtists, 0, SpringLayout.WEST, listScrollerArtist);
		lblArtists.setFont(font.deriveFont(attributes));

		springLayout.putConstraint(SpringLayout.WEST, lblAlbums, 0, SpringLayout.WEST, listScrollerAlbum);
		springLayout.putConstraint(SpringLayout.SOUTH, lblAlbums, -6, SpringLayout.NORTH, listScrollerAlbum);
		lblAlbums.setFont(font.deriveFont(attributes));

		add(listScrollerGenre);
		add(listScrollerArtist);
		add(listScrollerAlbum);
		add(btnCheckOut);
		add(lblGenres);
		add(lblArtists);
		add(lblAlbums);
	}
}
