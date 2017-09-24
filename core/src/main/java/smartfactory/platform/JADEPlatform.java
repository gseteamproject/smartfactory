package smartfactory.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public DFAgentDescription[] search(Agent a, DFAgentDescription dfd) {
		try {
			return DFService.search(a, dfd);
		} catch (FIPAException e) {
			logger.error("", e);
		}
		return new DFAgentDescription[0];
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
