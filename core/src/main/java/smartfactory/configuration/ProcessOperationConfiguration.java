package smartfactory.configuration;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessOperationConfiguration {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	String name;

	public ProcessOperationConfiguration() {
	}

	public ProcessOperationConfiguration(String name) {
		this.name = name;
	}

	public void load(Element root) {
		loadName(root);
	}

	void loadName(Element root) {
		Element element = root.getChild(ConfigurationTag.PROCESS_OPERATION_NAME);
		name = element.getTextTrim();
		logger.info("{} : {}", ConfigurationTag.PROCESS_OPERATION_NAME, name);
	}

	public String getName() {
		return name;
	}
}
