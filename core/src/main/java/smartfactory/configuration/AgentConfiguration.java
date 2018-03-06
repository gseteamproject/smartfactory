package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentConfiguration {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String name;

	public String className;

	public List<String> parameters;

	public AgentConfiguration() {
		this.parameters = new ArrayList<String>();
	}

	public void load(Element root) {
		loadName(root);
		loadClassName(root);
		loadParameters(root);
	}

	void loadName(Element root) {
		Element element = root.getChild(Tag.AGENT_NAME);
		name = element.getTextTrim();
		logger.info("{} : {}", Tag.AGENT_NAME, name);
	}

	void loadClassName(Element root) {
		Element element = root.getChild(Tag.AGENT_CLASS);
		className = element.getTextTrim();
		logger.info("{} : {}", Tag.AGENT_CLASS, className);
	}

	void loadParameters(Element root) {
		Element element = root.getChild(Tag.AGENT_PARAMETERS);
		if (element != null) {
			for (Element e : element.getChildren(Tag.AGENT_PARAMETERS_PARAMETER)) {
				parameters.add(e.getTextTrim());
			}
		}
		logger.info("{} : {}", Tag.AGENT_PARAMETERS, parameters);
	}

	public String getAgentName() {
		return name;
	}

	public String getAgentClass() {
		return className;
	}

	public Object[] getAgentParameters() {
		return parameters.toArray();
	}
}
