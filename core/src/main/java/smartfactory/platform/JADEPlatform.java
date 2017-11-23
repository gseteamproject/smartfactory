package smartfactory.platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.Boot;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

public class JADEPlatform implements AgentPlatform {

	private Agent agent;

	public JADEPlatform() {
	}

	public JADEPlatform(Agent agent) {
		this.agent = agent;
	}

	@Override
	public void launch(String[] args) {
		Boot.main(args);
	}

	@Override
	public List<DFAgentDescription> search(DFAgentDescription dfd) {
		try {
			return Arrays.asList(DFService.search(agent, dfd));
		} catch (FIPAException e) {
			logger.error("", e);
		}
		return new ArrayList<DFAgentDescription>();
	}

	@Override
	public Agent getThisAgent() {
		return agent;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
