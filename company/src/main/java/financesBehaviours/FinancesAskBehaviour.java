package financesBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.AskBehaviour;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class FinancesAskBehaviour extends AskBehaviour {

	private static final long serialVersionUID = 2249201124835167657L;

	private MessageObject msgObj;

	public FinancesAskBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public void action() {
		if (!this.isStarted()) {
			ACLMessage request = interactionBehaviour.getRequest();
			Order order = Order.gson.fromJson(request.getContent(), Order.class);
			String orderText = order.getTextOfOrder();

			if (order.searchInList(CrossAgentData.orderQueue) > -1) {
				if (request.getConversationId() == "Order") {
					this.interactor.isDone = false;

					msgObj = new MessageObject(request, " has accepted selling of " + orderText);
					dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

					CrossAgentData.orderQueue
							.get(order.searchInList(CrossAgentData.orderQueue)).agent = interactionBehaviour.getAgent()
									.getLocalName();

					myAgent.addBehaviour(new TransferMoneyToBank(interactionBehaviour));
				} else if (request.getConversationId() == "Materials") {
					this.interactor.isDone = false;

					msgObj = new MessageObject(request, "has accepted buying of " + orderText);
					dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

					CrossAgentData.orderQueue
							.get(order.searchInList(CrossAgentData.orderQueue)).agent = interactionBehaviour.getAgent()
									.getLocalName();

					myAgent.addBehaviour(new TransferMoneyFromBank(interactionBehaviour));
				}
			}
			this.setStarted(true);
		}
	}
}
