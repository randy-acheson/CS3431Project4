package gui.main;

import gui.login.Login;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import controllers.CDController;
import controllers.UserController;

import java.io.File;

import models.CDModel;
import models.UserModel;


public class MainLoader {

	public static void main(String[] args) {
		
		CDModel cdmodel = new CDModel();
		CDController.getInstance().setCDModel(cdmodel);
		
		UserModel userModel = new UserModel();
		UserController.getInstance().setUserModel(userModel);

		Login login = new Login();
		login.setVisible(true);
	}

	public static void loadXML() {
		
		try {
			File fXmlFile = new File("src/gui/cds.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("cd");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				System.out.println(" ");
				Element eElement = (Element) nNode;

				System.out.println("Genre: " + eElement.getElementsByTagName("genre").item(0).getTextContent());
				System.out.println("Artist: " + eElement.getElementsByTagName("artist").item(0).getTextContent());
				System.out.println("Title: " + eElement.getElementsByTagName("title").item(0).getTextContent());
			}
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
