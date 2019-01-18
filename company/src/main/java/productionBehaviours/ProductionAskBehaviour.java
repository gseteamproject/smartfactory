package productionBehaviours;

import basicAgents.SalesMarketAgent;
import basicClasses.Order;
import interactors.AskBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class ProductionAskBehaviour extends AskBehaviour {

	private static final long serialVersionUID = -4443443755165652310L;

	public ProductionAskBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public void action() {
		if (!this.isStarted()) {
			Order orderToProduce = Order.gson.fromJson(interactionBehaviour.getRequest().getContent(), Order.class);
			if (orderToProduce.searchInList(SalesMarketAgent.orderQueue) > -1) {
				// if (!this.isStarted()) {
				SalesMarketAgent.orderQueue
						.get(orderToProduce.searchInList(SalesMarketAgent.orderQueue)).agent = interactionBehaviour
								.getAgent().getLocalName();

				// myAgent.addBehaviour(new ProductionActivityBehaviour((ProductionResponder)
				// interactionBehaviour, (ProductionRequestResult) interactor, dataStore));
				myAgent.addBehaviour(new AskForMaterialsBehaviour(interactionBehaviour, dataStore));
				// }
				// this.setStarted(true);
			}
			this.setStarted(true);
		}
	}
}
