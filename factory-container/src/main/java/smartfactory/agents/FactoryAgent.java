package smartfactory.agents;

import smartfactory.behaviours.base.EventSubscriptionInitiatorBehaviour;
import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.EventHandler;
import smartfactory.models.Factory;
import smartfactory.models.Resource;
import smartfactory.models.ResourceOperation;

public class FactoryAgent extends ResourceAgent {

	@Override
	public Resource createResource() {
		Resource machine = new Factory();
		machine.addOperation(new BlockProductionOperation());
		return machine;
	}

	private class BlockProductionOperation extends ResourceOperation {

		public BlockProductionOperation() {
			super("block-production");
		}

		@Override
		public void execute() {
			executed = false;
			String processAgentName = BlockProcessAgent.getUniqueName();

			AgentConfiguration subAgentConfiguration = new AgentConfiguration();
			subAgentConfiguration.name = processAgentName;
			subAgentConfiguration.className = BlockProcessAgent.class.getName();
			agentDataStore.setSubAgentConfiguration(subAgentConfiguration);

			addBehaviour(new LaunchAgentBehaviour(agentDataStore));
			addBehaviour(
					new EventSubscriptionInitiatorBehaviour(processAgentName, "process-status", new EventHandler() {
						@Override
						public void callback() {
							operationCompleted();
						}
					}));
		}
	}

	private static final long serialVersionUID = 4282751471381265727L;
}
