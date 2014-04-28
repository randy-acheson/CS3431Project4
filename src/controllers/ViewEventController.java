package controllers;

import gui.main.MainCDFrame;


public class ViewEventController {

	private static ViewEventController instance;
	private MainCDFrame mainCDFrame;

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

}
