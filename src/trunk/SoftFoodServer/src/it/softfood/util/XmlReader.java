package it.softfood.util;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class XmlReader {
	
	private final String xmlFile="InitialConfiguration.xml";
	private Document document;
	
	public XmlReader() {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(xmlFile);
			document = parser.getDocument();           
		} 
		catch (Exception e) {
			System.err.println(e);
		}
	}

	public String leggi(String properties){
		return this.leggi(properties,document,0);
	}

	private String leggi(String properties, Node node, int numLevelsDeep) {
		String s=null;
		int type = node.getNodeType();
		if (type == Node.ELEMENT_NODE){
			if(properties.equals(node.getNodeName())){
				s=node.getFirstChild().getNodeValue();

				return s;
			}
		}
		NodeList children = node.getChildNodes();
		if (children != null) {
			for (int i=0; i<children.getLength(); i++) {
				s=leggi(properties,children.item(i), numLevelsDeep+1);
				if(s!=null) return s;
			}

		}
		
		return s;
	}

}

