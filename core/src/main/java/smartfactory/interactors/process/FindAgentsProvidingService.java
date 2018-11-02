package smartfactory.interactors.process;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.interactors.OneShotInteractor;
import smartfactory.utility.AgentDataStore;

public class FindAgentsProvidingService extends OneShotInteractor {

	public FindAgentsProvidingService(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		ServiceDescription serviceDescriptionTemplate = new ServiceDescription();
		serviceDescriptionTemplate.setName(agentDataStore.getProcessOperation().serviceName);

		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(serviceDescriptionTemplate);

		List<DFAgentDescription> agentsProvidingService = agentDataStore.getAgentPlatform()
				.search(agentDescriptionTemplate);
		agentDataStore.getProcessOperation().agentsDescription = agentsProvidingService;
		logger.info("found \"{}\" agents providing \"{}\" service ", agentsProvidingService.size(),
				serviceDescriptionTemplate.getName());
	}

	@Override
	public int next() {
		return agentDataStore.getProcessOperation().isAgentsFound();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
