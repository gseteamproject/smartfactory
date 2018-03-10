package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentConfiguration {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	String agentName;

	String agentClass;

	List<String> parameters = new ArrayList<String>();

	ResourceConfiguration resourceConfiguration = new ResourceConfiguration();

	public AgentConfiguration() {
		this(null, null);
	}

	public AgentConfiguration(String agentName, String agentClass) {
		this.agentName = agentName;
		this.agentClass = agentClass;
	}

	public void load(Element root) {
		loadName(root);
		loadClassName(root);
		loadResourceConfiguration(root);
		loadParameters(root);
	}

	void loadName(Element root) {
		Element element = root.getChild(ConfigurationTag.AGENT_NAME);
		agentName = element.getTextTrim();
		logger.info("{} : {}", ConfigurationTag.AGENT_NAME, agentName);
	}

	void loadClassName(Element root) {
		Element element = root.getChild(ConfigurationTag.AGENT_CLASS);
		agentClass = element.getTextTrim();
		logger.info("{} : {}", ConfigurationTag.AGENT_CLASS, agentClass);
	}

	void loadParameters(Element root) {
		Element element = root.getChild(ConfigurationTag.AGENT_PARAMETERS);
		if (element != null) {
			for (Element e : element.getChildren(ConfigurationTag.AGENT_PARAMETERS_PARAMETER)) {
				parameters.add(e.getTextTrim());
			}
		}
		logger.info("{} : {}", ConfigurationTag.AGENT_PARAMETERS, parameters);
	}

	void loadResourceConfiguration(Element root) {
		Element element = root.getChild(ConfigurationTag.AGENT_RESOURCE);
		if (element != null) {
			resourceConfiguration.load(element);
		}
	}

	public String getAgentName() {
		return agentName;
	}

	public String getAgentClass() {
		return agentClass;
	}

	public Object[] getAgentParameters() {
		return new Object[] { this };
	}

	public ResourceConfiguration getResourceConfiguration() {
		return resourceConfiguration;
	}
}
