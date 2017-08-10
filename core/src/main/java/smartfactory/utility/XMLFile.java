package smartfactory.utility;

import java.io.IOException;
import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLFile {
	public String fileName;
	
	public XMLFile(String string) {
		// TODO Auto-generated constructor stub
	}

	public Element getRootElement() {
		/*
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(new StringReader(fileName));
			return doc.getRootElement();
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return null;
	}
}
