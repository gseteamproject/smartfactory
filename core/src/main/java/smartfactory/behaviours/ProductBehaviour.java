package smartfactory.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.PerformServiceInitiator;
import smartfactory.interactors.product.DetermineRequiredService;
import smartfactory.interactors.product.FindAgentsProvidingService;
import smartfactory.interactors.product.NoAgentsProvidingService;
import smartfactory.interactors.product.ProductIsInLastState;
import smartfactory.interactors.product.ProductProcessIsIncorrect;
import smartfactory.interactors.product.SelectAgentToPerformService;
import smartfactory.interactors.product.TransitProductToNextState;
import smartfactory.models.Order;
import smartfactory.models.Product;
import smartfactory.platform.JADEPlatform;

public class ProductBehaviour extends FSMBehaviour {

	public ProductBehaviour(Agent agent, Product product) {
		super(agent);

		ProductDataStore productDataStore = new ProductDataStore();
		productDataStore.setProduct(product);
		productDataStore.setOrder(new Order());
		productDataStore.setAgentPlatform(new JADEPlatform(agent));
		setDataStore(productDataStore);

		Behaviour b1 = new OneShotInteractorBehaviour(new DetermineRequiredService(productDataStore));
		Behaviour b2 = new OneShotInteractorBehaviour(new FindAgentsProvidingService(productDataStore));
		Behaviour b3 = new OneShotInteractorBehaviour(new SelectAgentToPerformService(productDataStore));
		Behaviour b4 = new AchieveREInitiatorInteractorBehaviour(agent, new PerformServiceInitiator(productDataStore));
		Behaviour b5 = new OneShotInteractorBehaviour(new TransitProductToNextState(productDataStore));
		Behaviour b6 = new OneShotInteractorBehaviour(new ProductIsInLastState(productDataStore));
		Behaviour b7 = new OneShotInteractorBehaviour(new ProductProcessIsIncorrect(productDataStore));
		Behaviour b8 = new OneShotInteractorBehaviour(new NoAgentsProvidingService(productDataStore));

		int b1_b2 = Order.ServiceDetermined;
		int b1_b7 = Order.ServiceNotDetermined;
		int b2_b3 = Order.AgentsFound;
		int b2_b8 = Order.AgentsNotFound;
		int b3_b4 = Order.AgentSelected;
		int b3_b2 = Order.AgentNotSelected;
		int b4_b5 = PerformServiceInitiator.ServicePerformedSuccessfully;
		int b4_b3 = PerformServiceInitiator.ServicePerformedUnSuccessfully;
		int b5_b6 = Product.IsInTheLastState;
		int b5_b1 = Product.IsNotInTheLastState;

		String b1_name = DetermineRequiredService.class.getSimpleName();
		String b2_name = FindAgentsProvidingService.class.getSimpleName();
		String b3_name = SelectAgentToPerformService.class.getSimpleName();

		String b5_name = TransitProductToNextState.class.getSimpleName();
		String b6_name = ProductIsInLastState.class.getSimpleName();
		String b7_name = ProductProcessIsIncorrect.class.getSimpleName();
		String b8_name = NoAgentsProvidingService.class.getSimpleName();

		registerFirstState(b1, b1_name);
		registerState(b2, b2_name);
		registerState(b3, b3_name);
		registerState(b4, b4.getBehaviourName());
		registerState(b5, b5_name);
		registerLastState(b6, b6_name);
		registerLastState(b7, b7_name);
		registerLastState(b8, b8_name);

		registerTransition(b1_name, b2_name, b1_b2);
		registerTransition(b1_name, b7_name, b1_b7);
		registerTransition(b2_name, b3_name, b2_b3);
		registerTransition(b2_name, b8_name, b2_b8);
		registerTransition(b3_name, b4.getBehaviourName(), b3_b4);
		registerTransition(b3_name, b2_name, b3_b2, new String[] { b2_name, b3_name });
		registerTransition(b4.getBehaviourName(), b5_name, b4_b5);
		registerTransition(b4.getBehaviourName(), b3_name, b4_b3, new String[] { b3_name, b4.getBehaviourName() });
		registerTransition(b5_name, b6_name, b5_b6);
		registerTransition(b5_name, b1_name, b5_b1,
				new String[] { b1_name, b2_name, b3_name, b4.getBehaviourName(), b5_name });
	}

	private static final long serialVersionUID = -7091209844136813253L;
}
