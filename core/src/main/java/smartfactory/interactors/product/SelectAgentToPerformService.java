package smartfactory.interactors.product;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.OneShotInteractor;

public class SelectAgentToPerformService extends ProductInteractor implements OneShotInteractor {

	private DFAgentDescription agentProvidingService;

	public SelectAgentToPerformService(ProductDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		List<DFAgentDescription> agentsProvidingService = dataStore.getServiceProvisioning().agentsDescription;
		if (agentsProvidingService.size() > 0) {
			agentProvidingService = agentsProvidingService.get(0);
			logger.info("\"{}\" agent selected", agentProvidingService.getName().getLocalName());
		} else {
			agentProvidingService = null;
			logger.info("agent not selected");
		}
		dataStore.getServiceProvisioning().agentDescription = agentProvidingService;
	}

	@Override
	public int next() {
		return dataStore.getServiceProvisioning().isAgentSelected();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
