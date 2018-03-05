package smartfactory.platform;

import java.util.List;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.configuration.ContainerConfiguration;

public interface AgentPlatform {

	public List<DFAgentDescription> search(DFAgentDescription dfd);

	public void registerAgentServices(DFAgentDescription dfd);

	public void deregisterAgentServices();

	public void startContainer(ContainerConfiguration containerConfiguration);

	public void startAgent(AgentConfiguration agentConfiguration);
}
