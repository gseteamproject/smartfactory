package smartfactory.interactors.process;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.OneShotInteractor;

public class SelectAgentToPerformService extends ProcessInteractor implements OneShotInteractor {

	private DFAgentDescription agentProvidingService;

	public SelectAgentToPerformService(ProcessDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		List<DFAgentDescription> agentsProvidingService = dataStore.getProcessOperation().agentsDescription;
		if (agentsProvidingService.isEmpty()) {
			agentProvidingService = null;
			logger.info("agent not selected");
		} else {
			agentProvidingService = agentsProvidingService.get(0);
			logger.info("\"{}\" agent selected", agentProvidingService.getName().getLocalName());
		}
		dataStore.getProcessOperation().agentDescription = agentProvidingService;
	}

	@Override
	public int next() {
		return dataStore.getProcessOperation().isAgentSelected();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
