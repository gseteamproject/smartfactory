package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

public class PlatformConfiguration {

	enum ContainerType {
		main, regular
	};

	public String host;

	public String localhost;

	public String containerName;

	public ContainerType containerType;

	public String getContainerName() {
		return containerName;
	}

	public List<String> getStartupParameters() {
		List<String> parameters = new ArrayList<String>();
		parameters.add("-gui");
		parameters.add("-container-name");
		parameters.add("my container");
		return parameters;
	}
}
