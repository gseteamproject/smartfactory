package smartfactory.platform;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

public interface AgentPlatform {

	public void launch(String args[]);

	public DFAgentDescription[] search(Agent a, DFAgentDescription dfd);
}
