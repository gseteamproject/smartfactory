package smartfactory.interactors.product;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.OneShotInteractor;

public class FindAgentsProvidingService extends ProductInteractor implements OneShotInteractor {

	public FindAgentsProvidingService(ProductDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		ServiceDescription serviceDescriptionTemplate = new ServiceDescription();
		serviceDescriptionTemplate.setName(dataStore.getServiceProvisioning().serviceName);

		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(serviceDescriptionTemplate);

		List<DFAgentDescription> agentsProvidingService = dataStore.getAgentPlatform().search(agentDescriptionTemplate);
		dataStore.getServiceProvisioning().agentsDescription = agentsProvidingService;
		logger.info("found \"{}\" agents providing \"{}\" service ", agentsProvidingService.size(),
				serviceDescriptionTemplate.getName());
	}

	@Override
	public int next() {
		return dataStore.getServiceProvisioning().isAgentsFound();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
