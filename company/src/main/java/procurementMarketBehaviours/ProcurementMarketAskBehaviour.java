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
			Order order = Order.fromJson(interactionBehaviour.getRequest().getContent());
			if (order.searchInList(CrossAgentData.orderQueue) > -1) {
				CrossAgentData.orderQueue
						.get(order.searchInList(CrossAgentData.orderQueue)).agent = interactionBehaviour.getAgent()
								.getLocalName();
				myAgent.addBehaviour(new ReportFinancesBehaviour(interactionBehaviour, dataStore));
			}
			this.setStarted(true);
		}
	}
}
