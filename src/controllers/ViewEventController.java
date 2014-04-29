package controllers;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import models.CDModel;
import gui.main.MainCDFrame;
import gui.main.WishListPanel;


public class ViewEventController {

	private static ViewEventController instance;
	private MainCDFrame mainCDFrame;
	private WishListPanel wishListPanel;
	List<String> wishListCDs = new ArrayList<String>();

	private ViewEventController () {}

	public static ViewEventController getInstance() {
		if (instance == null) {
			instance = new ViewEventController();
		}
		return instance;
	}
	
	public void setMainCDFrame(MainCDFrame mainCDFrame) {
		this.mainCDFrame = mainCDFrame;
	}
	
	public MainCDFrame getMainCDFrame() {
		return mainCDFrame;
	}

	public WishListPanel getWishListPanel() {
		return wishListPanel;
	}
	
	public void setWishListPanel(WishListPanel wishListPanel) {
		this.wishListPanel = wishListPanel;
	}

	public boolean isInWishList(String album) {
		if (wishListCDs.contains(album)) {
			return true;
		}
		else {
			return false;
		}
	}

	public List<String> getWishListCDs() {
		return wishListCDs;
	}

}
