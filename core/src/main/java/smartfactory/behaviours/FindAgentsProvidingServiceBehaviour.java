package smartfactory.behaviours;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.platform.AgentPlatform;
import smartfactory.platform.JADEPlatform;

public class FindAgentsProvidingServiceBehaviour extends OneShotBehaviour implements ProductBehaviour {

	private AgentPlatform jade;

	private List<DFAgentDescription> agentsProvidingService;

	public FindAgentsProvidingServiceBehaviour(Behaviour behaviour) {
		this(behaviour, new JADEPlatform());
	}

	public FindAgentsProvidingServiceBehaviour(Behaviour behaviour, AgentPlatform jade) {
		super(behaviour.getAgent());
		setDataStore(behaviour.getDataStore());
		this.jade = jade;
	}

	@Override
	public void action() {
		ServiceDescription serviceDescriptionTemplate = new ServiceDescription();
		serviceDescriptionTemplate.setName(getProductDataStore().getOrder().serviceName);

		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(serviceDescriptionTemplate);

		try {
			agentsProvidingService = Arrays.asList(jade.search(myAgent, agentDescriptionTemplate));
			getProductDataStore().getOrder().agentsDescription = agentsProvidingService;
			logger.info("found \"{}\" agents providing \"{}\" service ", agentsProvidingService.size(),
					serviceDescriptionTemplate.getName());
		} catch (FIPAException e) {
			logger.error("", e);
		}
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
