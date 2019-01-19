package salesMarketBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import interactors.AskBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class SalesMarketAskBehaviour extends AskBehaviour {

	private static final long serialVersionUID = 974888961701937616L;

	public SalesMarketAskBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public void action() {
		if (!this.isStarted()) {
			Order order = Order.gson.fromJson(dataStore.getRequestMessage().getContent(), Order.class);
			if (!CrossAgentData.orderQueue.contains(order)) {
				CrossAgentData.orderQueue.add(order);
				CrossAgentData.orderQueue
						.get(order.searchInList(CrossAgentData.orderQueue)).agent = interactionBehaviour.getAgent()
								.getLocalName();
				// if agent agrees it starts executing request
				// myAgent.addBehaviour(new SalesMarketActivityBehaviour((SalesMarketResponder)
				// interactionBehaviour, (SalesMarketRequestResult) interactor, dataStore));
				myAgent.addBehaviour(new AskForOrderBehaviour(interactionBehaviour, dataStore));
			}
			this.setStarted(true);
		}
	}
}
