package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlatformConfiguration {

	final static String TAG_PLATFORM = "platform";

	final static String TAG_HOST = "host";

	final static String TAG_LOCAL_HOST = "local-host";

	public String host;

	public String localhost;

	public PlatformConfiguration() {
	}

	public List<String> getStartupParameters() {
		List<String> parameters = new ArrayList<String>();
		appendHost(parameters);
		appendLocalHost(parameters);
		return parameters;
	}

	void appendHost(List<String> parameters) {
		if (StringUtils.isNotEmpty(host)) {
			parameters.add("-host");
			parameters.add(host);
		}
	}

	void appendLocalHost(List<String> parameters) {
		if (StringUtils.isNotEmpty(localhost)) {
			parameters.add("-local-host");
			parameters.add(localhost);
		}
	}

	public void load(Element root) {
		loadHost(root);
		loadLocalHost(root);
	}

	void loadHost(Element root) {
		Element element = root.getChild(TAG_HOST);
		if (element == null) {
			host = null;
		} else {
			host = element.getTextTrim();
		}
		logger.info("{} : {}", TAG_HOST, host);
	}

	void loadLocalHost(Element root) {
		Element element = root.getChild(TAG_LOCAL_HOST);
		if (element == null) {
			localhost = null;
		} else {
			localhost = element.getTextTrim();
		}
		logger.info("{} : {}", TAG_LOCAL_HOST, localhost);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
