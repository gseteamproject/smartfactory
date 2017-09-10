package smartfactory.platform;

import jade.Boot;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

public class JADEPlatform implements AgentPlatform {

	@Override
	public void launch(String[] args) {
		Boot.main(args);
	}

	@Override
	public DFAgentDescription[] search(Agent a, DFAgentDescription dfd) throws FIPAException {
		return DFService.search(a, dfd);
	}
}
