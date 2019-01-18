package financesBehaviours;

import basicAgents.SalesMarketAgent;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.AskBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class FinancesAskBehaviour extends AskBehaviour {

	private static final long serialVersionUID = 2249201124835167657L;
	private MessageObject msgObj;

	public FinancesAskBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public void action() {
		if (!this.isStarted()) {
			ACLMessage request = interactionBehaviour.getRequest();
			Order order = Order.gson.fromJson(request.getContent(), Order.class);
			String orderText = order.getTextOfOrder();

			if (order.searchInList(SalesMarketAgent.orderQueue) > -1) {
				if (request.getConversationId() == "Order") {
					// if (!this.isStarted()) {
					this.interactor.isDone = false;
					msgObj = new MessageObject(request, " has accepted selling of " + orderText);
					Communication.server.sendMessageToClient(msgObj);

					SalesMarketAgent.orderQueue
							.get(order.searchInList(SalesMarketAgent.orderQueue)).agent = interactionBehaviour
									.getAgent().getLocalName();

					myAgent.addBehaviour(new TransferMoneyToBank(interactionBehaviour));
					// myAgent.addBehaviour(new FinancesActivityBehaviour((FinancesResponder)
					// interactionBehaviour, (FinancesRequestResult) interactor, dataStore));
					// }
					// this.setStarted(true);
				} else if (request.getConversationId() == "Materials") {
					// if (this.isStarted()) {
					this.interactor.isDone = false;
					msgObj = new MessageObject(request, "has accepted buying of " + orderText);
					Communication.server.sendMessageToClient(msgObj);

					SalesMarketAgent.orderQueue
							.get(order.searchInList(SalesMarketAgent.orderQueue)).agent = interactionBehaviour
									.getAgent().getLocalName();

					myAgent.addBehaviour(new TransferMoneyFromBank(interactionBehaviour));
					// myAgent.addBehaviour(new FinancesActivityBehaviour((FinancesResponder)
					// interactionBehaviour, (FinancesRequestResult) interactor, dataStore));
					// }
					// this.setStarted(false);
				}
			}
			this.setStarted(true);
		}
	}
}
