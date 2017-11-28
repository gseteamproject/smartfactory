package smartfactory.platform;

import java.util.List;

import jade.domain.FIPAAgentManagement.DFAgentDescription;

public interface AgentPlatform {

	public void launch(String args[]);

	public List<DFAgentDescription> search(DFAgentDescription dfd);
	
	public void startAgent(String agentName, String agentClass);
}
