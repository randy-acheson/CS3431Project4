package controllers;

import models.CDModel;

public class CDController {
	private static CDController instance = null;
	private CDModel cdmodel;
	
	private CDController () {}
	
	public static CDController getInstance() {
		if (instance == null) {
			instance = new CDController();
		}
		return instance;
	}
	
	public void setCDModel(CDModel cdmodel) {
		this.cdmodel = cdmodel;
	}
	
	public CDModel getCDModel () {
		return cdmodel;
	}

}
