package models;

import gui.main.CD;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CDModel {
	List<CD> CDCollection = new ArrayList<CD>();
	
	public CDModel () {
		try {
			// read XML file
			File fXmlFile = new File("src/models/cds.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			NodeList nList = doc.getElementsByTagName("cd");
			String genre;
			String artist;
			String title;
			
			// Add all CDs to list of CD
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				
				genre = eElement.getElementsByTagName("genre").item(0).getTextContent();
				artist = eElement.getElementsByTagName("artist").item(0).getTextContent();
				title = eElement.getElementsByTagName("title").item(0).getTextContent();

				addCD(new CD(genre, artist, title));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void addCD(CD cd) {
		CDCollection.add(cd);
	}

	public List<CD> getCDCollection() {
		return CDCollection;
	}
	
}
