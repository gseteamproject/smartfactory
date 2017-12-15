package smartfactory.agents;

import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.Factory;
import smartfactory.models.Machine;
import smartfactory.models.Operation;

public class FactoryAgent extends MachineAgent {

	@Override
	public Machine createMachine() {
		Machine machine = new Factory();
		machine.addOperation(new BlockProductionOperation());
		return machine;
	}

	private class BlockProductionOperation extends Operation {

		public BlockProductionOperation() {
			super("block-production");
		}

		@Override
		public void execute() {
			super.execute();

			AgentConfiguration subAgentConfiguration = new AgentConfiguration();
			subAgentConfiguration.name = BlockAgent.getUniqueName();
			subAgentConfiguration.className = BlockAgent.class.getName();
			dataStore.setSubAgentConfiguration(subAgentConfiguration);

			addBehaviour(new LaunchAgentBehaviour(dataStore));
		}
	}

	private static final long serialVersionUID = 4282751471381265727L;
}
