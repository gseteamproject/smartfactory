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
import jade.wrapper.StaleProxyException;

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
			return new ArrayList<DFAgentDescription>(Arrays.asList(DFService.search(agent, dfd)));
		} catch (FIPAException e) {
			logger.error("search failed", e);
		}
		return new ArrayList<DFAgentDescription>();
	}

	@Override
	public void startAgent(String agentName, String agentClass) {
		try {
			agent.getContainerController().createNewAgent(agentName, agentClass, null).start();
		} catch (StaleProxyException e) {
			logger.error("createNewAgent failed", e);
		}
	}

	@Override
	public void registerAgentServices(DFAgentDescription dfd) {
		try {
			DFService.register(agent, dfd);
		} catch (FIPAException exception) {
			logger.error("register failed", exception);
		}
	}

	@Override
	public void deregisterAgentServices() {
		try {
			DFService.deregister(agent);
		} catch (FIPAException exception) {
			logger.error("deregister failed", exception);
		}
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
