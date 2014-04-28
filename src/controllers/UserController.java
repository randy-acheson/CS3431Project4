package controllers;

import models.UserModel;

public class UserController {
	private static UserController instance = null;
	private UserModel userModel;
	
	private UserController () {}
	
	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}
	
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
	public UserModel getUserModel () {
		return userModel;
	}

}
