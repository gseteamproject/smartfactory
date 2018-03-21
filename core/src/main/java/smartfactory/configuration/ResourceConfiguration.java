package smartfactory.configuration;

import java.io.Serializable;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.models.ResourceType;

public class ResourceConfiguration implements Serializable {

	private static final long serialVersionUID = -1758132012598449481L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	ResourceType resourceType = ResourceType.none;

	public void load(Element root) {
		loadResourceType(root);
	}

	void loadResourceType(Element root) {
		Element element = root.getChild(ConfigurationTag.RESOURCE_TYPE);
		if (element != null) {
			resourceType = ResourceType.valueOf(element.getTextTrim());
		}
		logger.info("{} : {}", ConfigurationTag.RESOURCE_TYPE, resourceType);
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

}
