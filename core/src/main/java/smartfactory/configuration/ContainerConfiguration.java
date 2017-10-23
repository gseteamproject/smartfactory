package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.container.ContainerType;

public class ContainerConfiguration {

	final static String TAG_CONTAINER = "container";

	final static String TAG_CONTAINER_NAME = "name";

	final static String TAG_CONTAINER_TYPE = "type";

	final static String TAG_RMA = "rma";

	public String containerName;

	public ContainerType containerType;

	public boolean rma;

	public List<String> getStartupParameters() {
		List<String> parameters = new ArrayList<String>();
		appendContainerName(parameters);
		appendContainerType(parameters);
		appendGui(parameters);
		return parameters;
	}

	void appendContainerName(List<String> parameters) {
		if (StringUtils.isNotEmpty(containerName)) {
			parameters.add("-container-name");
			parameters.add(containerName);
		}
	}

	void appendGui(List<String> parameters) {
		if (rma) {
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

	public void load(Element root) {
		loadContainerName(root);
		loadContainerType(root);
		loadRma(root);
	}

	void loadContainerName(Element root) {
		Element element = root.getChild(TAG_CONTAINER_NAME);
		if (element == null) {
			containerName = null;
		} else {
			containerName = element.getTextTrim();
		}
		logger.info("{} : {}", TAG_CONTAINER_NAME, containerName);
	}

	void loadContainerType(Element root) {
		Element element = root.getChild(TAG_CONTAINER_TYPE);
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
		logger.info("{} : {}", TAG_CONTAINER_TYPE, containerType);
	}

	void loadRma(Element root) {
		Element element = root.getChild(TAG_RMA);
		if (element == null) {
			rma = false;
		} else {
			rma = Boolean.parseBoolean(element.getTextTrim());
		}
		logger.info("rma : {}", rma);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
