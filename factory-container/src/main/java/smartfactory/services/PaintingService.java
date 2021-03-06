package smartfactory.services;

import java.util.List;

import smartfactory.agents.ProcessAgent;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.configuration.ProcessOperationConfiguration;
import smartfactory.utility.AgentDataStore;

public class PaintingService extends LaunchProcessService {

	public PaintingService(AgentDataStore agentDataStore) {
		super(Services.painting, agentDataStore);
	}

	@Override
	public void execute() {
		String agentName = ProcessAgent.getUniqueName();
		String agentClass = ProcessAgent.class.getName();

		AgentConfiguration agentConfiguration = new AgentConfiguration(agentName, agentClass);
		List<ProcessOperationConfiguration> operationConfigurations = agentConfiguration.getProcessConfiguration()
				.getOperationsConfigurations();
		operationConfigurations.add(new ProcessOperationConfiguration(Services.transport));
		operationConfigurations.add(new ProcessOperationConfiguration(Services.painting_worker));

		agentDataStore.setSubAgentConfiguration(agentConfiguration);

		super.execute();
	}
}
