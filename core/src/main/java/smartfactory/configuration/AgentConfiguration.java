package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentConfiguration {

	static final String TAG_AGENT = "agent";

	static final String TAG_NAME = "name";

	static final String TAG_CLASS_NAME = "class";

	static final String TAG_PARAMETERS = "parameters";

	static final String TAG_PARAMETER = "parameter";

	public String name;

	public String className;

	public List<String> parameters;

	public AgentConfiguration() {
		this.parameters = new ArrayList<String>();
	}

	public String getStartupParameters() {
		String startupParameters = name + ":" + className;
		if (!parameters.isEmpty()) {
			startupParameters += "(";
			for (String parameter : parameters) {
				startupParameters += parameter + ",";
			}
			startupParameters = startupParameters.substring(0, startupParameters.length() - 1);
			startupParameters += ")";
		}
		return startupParameters;
	}

	public void load(Element root) {
		loadName(root);
		loadClassName(root);
		loadParameters(root);
	}

	void loadName(Element root) {
		Element element = root.getChild(TAG_NAME);
		name = element.getTextTrim();
		logger.info("{} : {}", TAG_NAME, name);
	}

	void loadClassName(Element root) {
		Element element = root.getChild(TAG_CLASS_NAME);
		className = element.getTextTrim();
		logger.info("{} : {}", TAG_CLASS_NAME, className);
	}

	void loadParameters(Element root) {
		Element element = root.getChild(TAG_PARAMETERS);
		if (element != null) {
			List<Element> elements = element.getChildren(TAG_PARAMETER);
			for (Element e : elements) {
				parameters.add(e.getTextTrim());
			}
		}
		logger.info("{} : {}", TAG_PARAMETERS, parameters);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
