package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.models.ResourceType;

public class AgentConfiguration {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	String agentName;

	String agentClass;

	List<String> parameters = new ArrayList<String>();

	ResourceType resourceType = ResourceType.none;

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
		loadResourceType(root);
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

	void loadResourceType(Element root) {
		Element element = root.getChild(ConfigurationTag.AGENT_RESOURCE_TYPE);
		if (element != null) {
			resourceType = ResourceType.valueOf(element.getTextTrim());
		}
		logger.info("{} : {}", ConfigurationTag.AGENT_RESOURCE_TYPE, resourceType);
	}

	public String getAgentName() {
		return agentName;
	}

	public String getAgentClass() {
		return agentClass;
	}

	public Object[] getAgentParameters() {
		return parameters.toArray();
	}

	public ResourceType getResourceType() {
		return resourceType;
	}
}
