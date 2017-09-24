package smartfactory.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.DetermineRequiredService;
import smartfactory.interactors.product.FindAgentsProvidingService;
import smartfactory.models.Order;
import smartfactory.models.Product;
import smartfactory.platform.JADEPlatform;

public class ProductMainBehaviour extends FSMBehaviour implements ProductBehaviour {

	public ProductMainBehaviour(Agent agent, Product product) {
		super(agent);

		ProductDataStore productDataStore = new ProductDataStore();
		productDataStore.setProduct(product);
		productDataStore.setOrder(new Order());
		productDataStore.setAgentPlatform(new JADEPlatform(agent));
		setDataStore(productDataStore);

		Behaviour b1 = new OneShotInteractorBehaviour(new DetermineRequiredService(productDataStore));
		Behaviour b2 = new OneShotInteractorBehaviour(new FindAgentsProvidingService(productDataStore));
		Behaviour b3 = new SelectAgentToPerformServiceBehaviour(this);
		Behaviour b4 = new AskSelectedAgentToPerformServiceBehaviour(this);
		Behaviour b5 = new TransitProductToNextStateBehaviour(this);
		Behaviour b6 = new ProductIsInLastStateBehaviour(this);
		Behaviour b7 = new ProductProcessIsIncorrectBehaviour(this);
		Behaviour b8 = new NoAgentsProvidingServiceBehaviour(this);

		int b1_b2 = Order.ServiceDetermined;
		int b1_b7 = Order.ServiceNotDetermined;
		int b2_b3 = Order.AgentsFound;
		int b2_b8 = Order.AgentsNotFound;
		int b3_b4 = SelectAgentToPerformServiceBehaviour.AgentSelected;
		int b3_b2 = SelectAgentToPerformServiceBehaviour.AgentNotSelected;
		int b4_b5 = AskSelectedAgentToPerformServiceBehaviour.ServicePerformedSuccessfully;
		int b4_b3 = AskSelectedAgentToPerformServiceBehaviour.ServicePerformedUnSuccessfully;
		int b5_b6 = TransitProductToNextStateBehaviour.ProductIsInTheLastState;
		int b5_b1 = TransitProductToNextStateBehaviour.ProductIsNotInTheLastState;

		registerFirstState(b1, b1.getBehaviourName());
		registerState(b2, b2.getBehaviourName());
		registerState(b3, b3.getBehaviourName());
		registerState(b4, b4.getBehaviourName());
		registerState(b5, b5.getBehaviourName());
		registerLastState(b6, b6.getBehaviourName());
		registerLastState(b7, b7.getBehaviourName());
		registerLastState(b8, b8.getBehaviourName());

		registerTransition(b1.getBehaviourName(), b2.getBehaviourName(), b1_b2);
		registerTransition(b1.getBehaviourName(), b7.getBehaviourName(), b1_b7);
		registerTransition(b2.getBehaviourName(), b3.getBehaviourName(), b2_b3);
		registerTransition(b2.getBehaviourName(), b8.getBehaviourName(), b2_b8);
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

	@Override
	public ProductDataStore getProductDataStore() {
		return (ProductDataStore) getDataStore();
	}
}
