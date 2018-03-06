package smartfactory.utility;

import java.io.FileInputStream;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLFile {

	String fileName;

	public XMLFile(String fileName) {
		this.fileName = fileName;
	}

	public Element getRootElement() {
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(new FileInputStream(fileName));
			return doc.getRootElement();
		} catch (JDOMException | IOException e) {
			logger.error("", e);
		}
		return null;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
