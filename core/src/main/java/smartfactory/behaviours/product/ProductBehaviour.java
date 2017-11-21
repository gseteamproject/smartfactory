package smartfactory.behaviours.product;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.ServiceProvisioning;
import smartfactory.models.Product;

public class ProductBehaviour extends FSMBehaviour {

	public ProductBehaviour(Agent agent, ProductDataStore productDataStore) {
		super();

		Behaviour b1 = new DetermineRequiredServiceBehaviour(productDataStore);
		Behaviour b2 = new FindAgentsProvidingServiceBehaviour(productDataStore);
		Behaviour b3 = new SelectAgentToPerformServiceBehaviour(productDataStore);
		Behaviour b4 = new ServiceProvisioningInitiatorBehaviour(agent, productDataStore);
		Behaviour b5 = new TransitProductToNextStateBehaviour(productDataStore);
		Behaviour b6 = new ProductIsInLastStateBehaviour(productDataStore);
		Behaviour b7 = new ProductProcessIsIncorrectBehaviour(productDataStore);
		Behaviour b8 = new NoAgentsProvidingServiceBehaviour(productDataStore);

		int b1_b2 = ServiceProvisioning.ServiceDetermined;
		int b1_b7 = ServiceProvisioning.ServiceNotDetermined;
		int b2_b3 = ServiceProvisioning.AgentsFound;
		int b2_b8 = ServiceProvisioning.AgentsNotFound;
		int b3_b4 = ServiceProvisioning.AgentSelected;
		int b3_b2 = ServiceProvisioning.AgentNotSelected;
		int b4_b5 = ServiceProvisioning.ServicePerformedSuccessfully;
		int b4_b3 = ServiceProvisioning.ServicePerformedUnSuccessfully;
		int b5_b6 = Product.IsInTheLastState;
		int b5_b1 = Product.IsNotInTheLastState;

		String b1_name = b1.getBehaviourName();
		String b2_name = b2.getBehaviourName();
		String b3_name = b3.getBehaviourName();
		String b4_name = b4.getBehaviourName();
		String b5_name = b5.getBehaviourName();
		String b6_name = b6.getBehaviourName();
		String b7_name = b7.getBehaviourName();
		String b8_name = b8.getBehaviourName();

		registerFirstState(b1, b1_name);
		registerState(b2, b2_name);
		registerState(b3, b3_name);
		registerState(b4, b4_name);
		registerState(b5, b5_name);
		registerLastState(b6, b6_name);
		registerLastState(b7, b7_name);
		registerLastState(b8, b8_name);

		registerTransition(b1_name, b2_name, b1_b2);
		registerTransition(b1_name, b7_name, b1_b7);
		registerTransition(b2_name, b3_name, b2_b3);
		registerTransition(b2_name, b8_name, b2_b8);
		registerTransition(b3_name, b4_name, b3_b4);
		registerTransition(b3_name, b2_name, b3_b2, new String[] { b2_name, b3_name });
		registerTransition(b4_name, b5_name, b4_b5);
		registerTransition(b4_name, b3_name, b4_b3, new String[] { b3_name, b4_name });
		registerTransition(b5_name, b6_name, b5_b6);
		registerTransition(b5_name, b1_name, b5_b1, new String[] { b1_name, b2_name, b3_name, b4_name, b5_name });
	}

	private static final long serialVersionUID = -7091209844136813253L;
}
