package smartfactory.interactors.order;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.OneShotInteractor;

public class SelectAgentToPerformProduction extends OrderInteractor implements OneShotInteractor {

	private DFAgentDescription agentProvidingService;

	public SelectAgentToPerformProduction(OrderDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		List<DFAgentDescription> agentsProvidingService = dataStore.getProductionProvisioning().agentsDescription;
		if (agentsProvidingService.size() > 0) {
			agentProvidingService = agentsProvidingService.get(0);
			logger.info("\"{}\" agent selected", agentProvidingService.getName());
		} else {
			agentProvidingService = null;
			logger.info("agent not selected");
		}
		dataStore.getProductionProvisioning().agentDescription = agentProvidingService;
	}

	@Override
	public int next() {
		return dataStore.getProductionProvisioning().isAgentSelected();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
