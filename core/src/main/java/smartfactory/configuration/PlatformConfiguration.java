package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.container.ContainerType;

public class PlatformConfiguration {

	public String containerName;

	public ContainerType containerType;

	public boolean gui;

	public String host;

	public String localhost;

	public PlatformConfiguration() {
	}

	public List<String> getStartupParameters() {
		List<String> parameters = new ArrayList<String>();
		appendContainerName(parameters);
		appendContainerType(parameters);
		appendGui(parameters);
		appendHost(parameters);
		appendLocalHost(parameters);
		return parameters;
	}

	void appendContainerName(List<String> parameters) {
		if (StringUtils.isNotEmpty(containerName)) {
			parameters.add("-container-name");
			parameters.add(containerName);
		}
	}

	void appendGui(List<String> parameters) {
		if (gui) {
			parameters.add("-gui");
		}
	}

	void appendContainerType(List<String> parameters) {
		switch (containerType == null ? ContainerType.MainContainer : containerType) {
		case Container:
			parameters.add("-container");
			break;
		case MainContainer:
		default:
			break;
		}
	}

	void appendHost(List<String> parameters) {
		if (StringUtils.isNotEmpty(host)) {
			parameters.add("-host");
			parameters.add(host);
		}
	}

	void appendLocalHost(List<String> parameters) {
		if (StringUtils.isNotEmpty(localhost)) {
			parameters.add("-local-host");
			parameters.add(localhost);
		}
	}

	public void load(Element root) {
		loadContainerName(root);
		loadContainerType(root);
		loadGui(root);
		loadHost(root);
		loadLocalHost(root);
	}

	void loadContainerName(Element root) {
		Element element = root.getChild("container-name");
		if (element == null) {
			containerName = null;
		} else {
			containerName = element.getTextTrim();
		}
		logger.info("container-name: {}", containerName);
	}

	void loadContainerType(Element root) {
		Element element = root.getChild("container-type");
		if (element == null) {
			containerType = ContainerType.MainContainer;
		} else {
			String text = element.getTextTrim();
			if (text.compareToIgnoreCase("container") == 0) {
				containerType = ContainerType.Container;
			} else {
				containerType = ContainerType.MainContainer;
			}
		}
		logger.info("container-type: {}", containerType);
	}

	void loadGui(Element root) {
		Element element = root.getChild("gui");
		if (element == null) {
			gui = false;
		} else {
			gui = Boolean.parseBoolean(element.getTextTrim());
		}
		logger.info("gui : {}", gui);
	}

	void loadHost(Element root) {
		Element element = root.getChild("host");
		if (element == null) {
			host = null;
		} else {
			host = element.getTextTrim();
		}
		logger.info("host : {}", host);
	}

	void loadLocalHost(Element root) {
		Element element = root.getChild("local-host");
		if (element == null) {
			localhost = null;
		} else {
			localhost = element.getTextTrim();
		}
		logger.info("local-host : {}", localhost);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
