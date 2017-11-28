package smartfactory.agents;

import smartfactory.models.Factory;
import smartfactory.models.Production;

public class FactoryAgent extends ProductionAgent {

	@Override
	public Production createProduction() {
		return new Factory();
	}

	private static final long serialVersionUID = 4282751471381265727L;
}
