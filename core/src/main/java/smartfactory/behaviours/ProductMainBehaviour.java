package smartfactory.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;
import smartfactory.accessors.ProductBehaviourDataStoreAccessor;

public class ProductMainBehaviour extends FSMBehaviour {

	protected ProductBehaviourDataStoreAccessor dataStoreAccessor;

	public ProductMainBehaviour(Agent agent) {
		super(agent);
		dataStoreAccessor = new ProductBehaviourDataStoreAccessor(getDataStore());

		Behaviour b1 = new DetermineRequiredService(this);
		Behaviour b2 = new FindAgentsProvidingService(this);
		Behaviour b3 = new SelectAgentToPerformService(this);
		Behaviour b4 = new AskSelectedAgentToPerformService(this);
		Behaviour b5 = new TransitProductToNextState(this);
		Behaviour b6 = new ProductIsInLastState(this);

		int b1_b2 = DetermineRequiredService.RequiredServiceIsDetermined;
		int b2_b3 = FindAgentsProvidingService.AgentsProvidingServiceFound;
		int b3_b4 = SelectAgentToPerformService.AgentToPerformServiceIsSelected;
		int b3_b2 = SelectAgentToPerformService.AgentToPerformServiceIsNotSelected;
		int b4_b5 = AskSelectedAgentToPerformService.ServicePerformedSuccessfully;
		int b4_b3 = AskSelectedAgentToPerformService.ServicePerformedUnSuccessfully;
		int b5_b6 = TransitProductToNextState.ProductIsNotInTheLastState;
		int b5_b1 = TransitProductToNextState.ProductIsInTheLastState;

		registerFirstState(b1, b1.getBehaviourName());
		registerState(b2, b2.getBehaviourName());
		registerState(b3, b3.getBehaviourName());
		registerState(b4, b4.getBehaviourName());
		registerState(b5, b5.getBehaviourName());
		registerLastState(b6, b6.getBehaviourName());

		registerTransition(b1.getBehaviourName(), b2.getBehaviourName(), b1_b2);
		registerTransition(b2.getBehaviourName(), b3.getBehaviourName(), b2_b3);
		registerTransition(b3.getBehaviourName(), b4.getBehaviourName(), b3_b4);
		registerTransition(b3.getBehaviourName(), b2.getBehaviourName(), b3_b2,
				new String[] { b2.getBehaviourName(), b3.getBehaviourName() });
		registerTransition(b4.getBehaviourName(), b5.getBehaviourName(), b4_b5);
		registerTransition(b4.getBehaviourName(), b3.getBehaviourName(), b4_b3,
				new String[] { b3.getBehaviourName(), b4.getBehaviourName() });
		registerTransition(b5.getBehaviourName(), b6.getBehaviourName(), b5_b6);
		registerTransition(b5.getBehaviourName(), b1.getBehaviourName(), b5_b1, new String[] { b1.getBehaviourName(),
				b2.getBehaviourName(), b3.getBehaviourName(), b4.getBehaviourName(), b5.getBehaviourName() });
	}

	private static final long serialVersionUID = -7091209844136813253L;
}
