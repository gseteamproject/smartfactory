package procurementMarketBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import common.AgentDataStore;
import interactors.AskBehaviour;
import interactors.ResponderBehaviour;

public class ProcurementMarketAskBehaviour extends AskBehaviour {

	private static final long serialVersionUID = -121063921816472827L;

	public ProcurementMarketAskBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public void action() {
		if (!this.isStarted()) {
			Order order = Order.gson.fromJson(interactionBehaviour.getRequest().getContent(), Order.class);
			if (order.searchInList(CrossAgentData.orderQueue) > -1) {
				// if (!this.isStarted()) {
				CrossAgentData.orderQueue
						.get(order.searchInList(CrossAgentData.orderQueue)).agent = interactionBehaviour.getAgent()
								.getLocalName();

				// myAgent.addBehaviour(new
				// ProcurementMarketActivityBehaviour((ProcurementMarketResponder)
				// interactionBehaviour, (ProcurementMarketRequestResult) interactor,
				// dataStore));
				// myAgent.addBehaviour(new AuctionInitiator((ProcurementMarketResponder)
				// interactionBehaviour));
				myAgent.addBehaviour(
						new ReportFinancesBehaviour(interactionBehaviour, dataStore));
				// }
				// this.setStarted(true);
			}
			this.setStarted(true);
		}
	}
}
