package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

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

	public void appendContainerName(List<String> parameters) {
		if (StringUtils.isNotEmpty(containerName)) {
			parameters.add("-container-name");
			parameters.add(containerName);
		}
	}

	public void appendGui(List<String> parameters) {
		if (gui) {
			parameters.add("-gui");
		}
	}

	public void appendContainerType(List<String> parameters) {
		switch (containerType == null ? ContainerType.MainContainer : containerType) {
		case Container:
			parameters.add("-container");
			break;
		case MainContainer:
		default:
			break;
		}
	}

	public void appendHost(List<String> parameters) {
		if (StringUtils.isNotEmpty(host)) {
			parameters.add("-host");
			parameters.add(host);
		}
	}

	public void appendLocalHost(List<String> parameters) {
		if (StringUtils.isNotEmpty(localhost)) {
			parameters.add("-local-host");
			parameters.add(localhost);
		}
	}
}
