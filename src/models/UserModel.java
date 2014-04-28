package models;

import gui.login.User;
import gui.main.CD;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UserModel {
	List<User> users = new ArrayList<User>();

	public UserModel() {
		try {
			// read XML file
			File fXmlFile = new File("src/models/user.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			NodeList nList = doc.getElementsByTagName("user");
			
			// Add all users to user model
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;

				String name = eElement.getElementsByTagName("name").item(0).getTextContent();
				String password = eElement.getElementsByTagName("password").item(0).getTextContent();

				addUser(new User(name, password));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addUser(User user) {
		users.add(user);
	}

	public List<User> getUsers() {
		return users;
	}
}