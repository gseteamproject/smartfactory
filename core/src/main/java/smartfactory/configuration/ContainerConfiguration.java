package smartfactory.configuration;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.container.ContainerType;

public class ContainerConfiguration {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	String containerName = null;

	ContainerType containerType = ContainerType.MainContainer;

	boolean rma = false;

	String host = null;

	String localhost = null;

	public void load(Element root) {
		loadContainerName(root);
		loadContainerType(root);
		loadRma(root);
		loadHost(root);
		loadLocalHost(root);
	}

	void loadHost(Element root) {
		Element element = root.getChild(ConfigurationTag.HOST);
		if (element != null) {
			host = element.getTextTrim();
		}
		logger.info("{} : {}", ConfigurationTag.HOST, host);
	}

	void loadLocalHost(Element root) {
		Element element = root.getChild(ConfigurationTag.LOCAL_HOST);
		if (element != null) {
			localhost = element.getTextTrim();
		}
		logger.info("{} : {}", ConfigurationTag.LOCAL_HOST, localhost);
	}

	void loadContainerName(Element root) {
		Element element = root.getChild(ConfigurationTag.CONTAINER_NAME);
		if (element != null) {
			containerName = element.getTextTrim();
		}
		logger.info("{} : {}", ConfigurationTag.CONTAINER_NAME, containerName);
	}

	void loadContainerType(Element root) {
		Element element = root.getChild(ConfigurationTag.CONTAINER_TYPE);
		if (element != null) {
			if (element.getTextTrim().compareToIgnoreCase("container") == 0) {
				containerType = ContainerType.Container;
			}
		}
		logger.info("{} : {}", ConfigurationTag.CONTAINER_TYPE, containerType);
	}

	void loadRma(Element root) {
		Element element = root.getChild(ConfigurationTag.RMA);
		if (element != null) {
			rma = Boolean.parseBoolean(element.getTextTrim());
		}
		logger.info("rma : {}", rma);
	}

	public String getContainerName() {
		return containerName;
	}

	public String getMainHost() {
		return host;
	}

	public String getLocalHost() {
		return localhost;
	}

	public boolean getGUI() {
		return rma;
	}

	public ContainerType getContainerType() {
		return containerType;
	}
}
