package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

	private PlatformConfiguration platform;

	private AgentsConfiguration agents;

	public Configuration() {
		platform = new PlatformConfiguration();
		agents = new AgentsConfiguration();
	}

	public Configuration(PlatformConfiguration platform, AgentsConfiguration agents) {
		this.platform = platform;
		this.agents = agents;
	}

	public String[] getStartupParameters() {
		List<String> parameters = new ArrayList<String>();
		parameters.addAll(platform.getStartupParameters());
		parameters.addAll(agents.getStartupParameters());
		return parameters.toArray(new String[parameters.size()]);
	}

	public void load() {
	}
}
