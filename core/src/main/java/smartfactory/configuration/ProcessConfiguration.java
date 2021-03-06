package smartfactory.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

public class ProcessConfiguration implements Serializable {

	private static final long serialVersionUID = -6641687841806236917L;

	List<ProcessOperationConfiguration> operationConfigurations = new ArrayList<ProcessOperationConfiguration>();

	public void load(Element root) {
		loadProcessOperationConfigurations(root);
	}

	void loadProcessOperationConfigurations(Element root) {
		Element operations = root.getChild(ConfigurationTag.PROCESS_OPERATIONS);
		if (operations != null) {
			for (Element operation : operations.getChildren(ConfigurationTag.PROCESS_OPERATION)) {
				ProcessOperationConfiguration operationConfiguration = new ProcessOperationConfiguration();
				operationConfiguration.load(operation);
				operationConfigurations.add(operationConfiguration);
			}
		}
	}

	public List<ProcessOperationConfiguration> getOperationsConfigurations() {
		return operationConfigurations;
	}
}
