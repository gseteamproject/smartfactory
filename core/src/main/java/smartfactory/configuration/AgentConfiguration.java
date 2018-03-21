package smartfactory.configuration;

import java.io.Serializable;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentConfiguration implements Serializable {

	private static final long serialVersionUID = 141253071465599556L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	String agentName;

	String agentClass;

	ResourceConfiguration resourceConfiguration = new ResourceConfiguration();

	ProcessConfiguration processConfiguration = new ProcessConfiguration();

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
		loadProcessConfiguration(root);
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

	void loadResourceConfiguration(Element root) {
		Element element = root.getChild(ConfigurationTag.RESOURCE);
		if (element != null) {
			resourceConfiguration.load(element);
		}
	}

	void loadProcessConfiguration(Element root) {
		Element element = root.getChild(ConfigurationTag.PROCESS);
		if (element != null) {
			processConfiguration.load(element);
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

	public ProcessConfiguration getProcessConfiguration() {
		return processConfiguration;
	}
}
