package smartfactory.services;

import java.util.List;

import smartfactory.agents.ProcessAgent;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.configuration.ProcessOperationConfiguration;
import smartfactory.utility.AgentDataStore;

public class BlockProductionService extends LaunchProcessService {

	public BlockProductionService(AgentDataStore agentDataStore) {
		super(Services.block_production, agentDataStore);
	}

	@Override
	public void execute() {
		String agentName = ProcessAgent.getUniqueName();
		String agentClass = ProcessAgent.class.getName();

		AgentConfiguration agentConfiguration = new AgentConfiguration(agentName, agentClass);
		List<ProcessOperationConfiguration> operationConfigurations = agentConfiguration.getProcessConfiguration()
				.getOperationsConfigurations();
		operationConfigurations.add(new ProcessOperationConfiguration(Services.store));
		operationConfigurations.add(new ProcessOperationConfiguration(Services.recognition));
		operationConfigurations.add(new ProcessOperationConfiguration(Services.cleaning));
		operationConfigurations.add(new ProcessOperationConfiguration(Services.painting));
		operationConfigurations.add(new ProcessOperationConfiguration(Services.packing));

		agentDataStore.setSubAgentConfiguration(agentConfiguration);

		super.execute();
	}
}
