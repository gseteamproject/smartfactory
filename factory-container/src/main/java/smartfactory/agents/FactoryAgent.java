package smartfactory.agents;

import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.Factory;
import smartfactory.models.Operation;
import smartfactory.models.Production;

public class FactoryAgent extends ProductionAgent {

	@Override
	public Production createProduction() {
		Production production = new Factory();
		production.addOperation(new BlockProductionOperation());
		return production;
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
