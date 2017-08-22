package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentConfiguration {

	public String name;

	public String className;

	public List<String> parameters;

	public AgentConfiguration() {
		this.parameters = new ArrayList<String>();
	}

	public String getStartupParameters() {
		String startupParameters = name + ":" + className;
		if (parameters.size() > 0) {
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
		Element element = root.getChild("name");
		name = element.getTextTrim();
		logger.info("name : {}", name);
	}

	void loadClassName(Element root) {
		Element element = root.getChild("class-name");
		className = element.getTextTrim();
		logger.info("class-name : {}", className);
	}

	void loadParameters(Element root) {
		Element element = root.getChild("parameters");
		if (element != null) {
			List<Element> elements = element.getChildren("parameter");
			for (Element e : elements) {
				parameters.add(e.getTextTrim());
			}
		}
		logger.info("parameters : {}", parameters);
	}

	final Logger logger = LoggerFactory.getLogger(AgentConfiguration.class);
}
