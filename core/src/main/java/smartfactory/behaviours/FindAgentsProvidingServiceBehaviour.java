package smartfactory.behaviours;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.dataStores.ProductDataStore;

public class FindAgentsProvidingServiceBehaviour extends OneShotBehaviour implements ProductBehaviour {

	public FindAgentsProvidingServiceBehaviour(Behaviour behaviour) {
		super(behaviour.getAgent());
		setDataStore(behaviour.getDataStore());
	}

	@Override
	public void action() {
		ServiceDescription serviceDescriptionTemplate = new ServiceDescription();
		serviceDescriptionTemplate.setName(getProductDataStore().getOrder().serviceName);

		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(serviceDescriptionTemplate);

		List<DFAgentDescription> agentsProvidingService = getProductDataStore().getAgentPlatform()
				.search(agentDescriptionTemplate);
		getProductDataStore().getOrder().agentsDescription = agentsProvidingService;
		logger.info("found \"{}\" agents providing \"{}\" service ", agentsProvidingService.size(),
				serviceDescriptionTemplate.getName());
	}

	@Override
	public int onEnd() {
		return getProductDataStore().getOrder().isAgentsFound();
	}

	@Override
	public ProductDataStore getProductDataStore() {
		return (ProductDataStore) getDataStore();
	}

	private static final long serialVersionUID = -6169428362127495247L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
