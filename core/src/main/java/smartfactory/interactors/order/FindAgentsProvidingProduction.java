package smartfactory.interactors.order;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.OneShotInteractor;

public class FindAgentsProvidingProduction extends OrderInteractor implements OneShotInteractor {

	public FindAgentsProvidingProduction(OrderDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		ServiceDescription serviceDescriptionTemplate = new ServiceDescription();
		serviceDescriptionTemplate.setName(dataStore.getProductionProvisioning().productionName);

		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(serviceDescriptionTemplate);

		List<DFAgentDescription> agentsProvidingService = dataStore.getAgentPlatform().search(agentDescriptionTemplate);
		dataStore.getProductionProvisioning().agentsDescription = agentsProvidingService;
		logger.info("found \"{}\" agents providing \"{}\" service ", agentsProvidingService.size(),
				serviceDescriptionTemplate.getName());
	}

	@Override
	public int next() {
		return dataStore.getProductionProvisioning().isAgentsFound();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
