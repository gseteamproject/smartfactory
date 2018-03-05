package smartfactory.container;

import smartfactory.configuration.AgentConfiguration;
import smartfactory.configuration.Configuration;
import smartfactory.platform.AgentPlatform;

public class Container {

	private AgentPlatform agentPlatform;

	public Container(AgentPlatform jade) {
		this.agentPlatform = jade;
	}

	public void launch(Configuration configuration) {
		agentPlatform.startContainer(configuration.getContainerConfiguration());
		for (AgentConfiguration agentConfiguration : configuration.getAgentConfigurations()) {
			agentPlatform.startAgent(agentConfiguration);
		}
	}
}
