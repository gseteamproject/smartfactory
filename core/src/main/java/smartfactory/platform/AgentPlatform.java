package smartfactory.platform;

import java.util.List;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

public interface AgentPlatform {

	public void launch(String args[]);

	public List<DFAgentDescription> search(DFAgentDescription dfd);

	public Agent getThisAgent();
}
