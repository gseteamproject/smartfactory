package smartfactory.interactors.process;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.interactors.OneShotInteractor;
import smartfactory.utility.AgentDataStore;

public class SelectAgentToPerformService extends OneShotInteractor {

	private DFAgentDescription agentProvidingService;

	public SelectAgentToPerformService(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		List<DFAgentDescription> agentsProvidingService = agentDataStore.getProcessOperation().agentsDescription;
		if (agentsProvidingService.isEmpty()) {
			agentProvidingService = null;
			logger.info("agent not selected");
		} else {
			int agentIdx = random.nextInt(agentsProvidingService.size());
			agentProvidingService = agentsProvidingService.get(agentIdx);
			logger.info("\"{}\" agent selected", agentProvidingService.getName().getLocalName());
		}
		agentDataStore.getProcessOperation().agentDescription = agentProvidingService;
	}

	@Override
	public int next() {
		return agentDataStore.getProcessOperation().isAgentSelected();
	}

	private static Random random = new Random();

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
