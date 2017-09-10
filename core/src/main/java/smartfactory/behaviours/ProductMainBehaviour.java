package smartfactory.behaviours;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import smartfactory.accessors.ProductBehaviourDataStoreAccessor;

public class ProductMainBehaviour extends FSMBehaviour {

	protected ProductBehaviourDataStoreAccessor dataStoreAccessor;

	public ProductMainBehaviour(Agent agent) {
		super(agent);
		dataStoreAccessor = new ProductBehaviourDataStoreAccessor(getDataStore());

		registerFirstState(new DetermineRequiredService(this), DetermineRequiredService.class.getSimpleName());
		registerState(new FindAgentsProvidingService(this), FindAgentsProvidingService.class.getSimpleName());
		registerState(new SelectAgentToPerformService(this), SelectAgentToPerformService.class.getSimpleName());
		registerState(new AskSelectedAgentToPerformService(this),
				AskSelectedAgentToPerformService.class.getSimpleName());
		registerState(new TransitProductToNextState(this), TransitProductToNextState.class.getSimpleName());
		registerLastState(new ProductIsInLastState(this), ProductIsInLastState.class.getSimpleName());

		registerTransition(DetermineRequiredService.class.getSimpleName(),
				FindAgentsProvidingService.class.getSimpleName(), DetermineRequiredService.RequiredServiceIsDetermined);
		registerTransition(FindAgentsProvidingService.class.getSimpleName(),
				SelectAgentToPerformService.class.getSimpleName(),
				FindAgentsProvidingService.AgentsProvidingServiceFound);
		registerTransition(SelectAgentToPerformService.class.getSimpleName(),
				AskSelectedAgentToPerformService.class.getSimpleName(),
				SelectAgentToPerformService.AgentToPerformServiceIsSelected);
		registerTransition(SelectAgentToPerformService.class.getSimpleName(),
				FindAgentsProvidingService.class.getSimpleName(),
				SelectAgentToPerformService.AgentToPerformServiceIsNotSelected,
				new String[] { SelectAgentToPerformService.class.getSimpleName(),
						FindAgentsProvidingService.class.getSimpleName() });
		registerTransition(AskSelectedAgentToPerformService.class.getSimpleName(),
				TransitProductToNextState.class.getSimpleName(),
				AskSelectedAgentToPerformService.ServicePerformedSuccessfully);
		registerTransition(AskSelectedAgentToPerformService.class.getSimpleName(),
				SelectAgentToPerformService.class.getSimpleName(),
				AskSelectedAgentToPerformService.ServicePerformedUnSuccessfully,
				new String[] { AskSelectedAgentToPerformService.class.getSimpleName(),
						SelectAgentToPerformService.class.getSimpleName(), });
		registerTransition(TransitProductToNextState.class.getSimpleName(), ProductIsInLastState.class.getSimpleName(),
				TransitProductToNextState.ProductIsNotInTheLastState);
		registerTransition(TransitProductToNextState.class.getSimpleName(),
				DetermineRequiredService.class.getSimpleName(), TransitProductToNextState.ProductIsInTheLastState,
				new String[] { DetermineRequiredService.class.getSimpleName(),
						FindAgentsProvidingService.class.getSimpleName(),
						SelectAgentToPerformService.class.getSimpleName(),
						AskSelectedAgentToPerformService.class.getSimpleName(),
						TransitProductToNextState.class.getSimpleName() });
	}

	private static final long serialVersionUID = -7091209844136813253L;
}
