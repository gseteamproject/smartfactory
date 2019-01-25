package sellingBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.AskBehaviour;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class SellingAskBehaviour extends AskBehaviour {

	private static final long serialVersionUID = -4443443755165652310L;

	private MessageObject msgObj;

	public SellingAskBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public void action() {
		if (!this.isStarted()) {
			ACLMessage request = interactionBehaviour.getRequest();
			Order order = Order.gson.fromJson(request.getContent(), Order.class);
			String orderText = order.getTextOfOrder();

			if (order.searchInList(CrossAgentData.orderQueue) > -1) {
				if (request.getConversationId() == "Ask") {
					this.interactor.isDone = false;
					msgObj = new MessageObject(request, "will check warehouse for " + orderText);
					dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

					CrossAgentData.orderQueue
							.get(order.searchInList(CrossAgentData.orderQueue)).agent = interactionBehaviour.getAgent()
									.getLocalName();

					myAgent.addBehaviour(new CheckWarehouseBehaviour(interactionBehaviour, dataStore));
				} else if (request.getConversationId() == "Take") {
					this.interactor.isDone = false;
					msgObj = new MessageObject(request, "will give order " + orderText + " from warehouse");
					dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

					CrossAgentData.orderQueue
							.get(order.searchInList(CrossAgentData.orderQueue)).agent = interactionBehaviour.getAgent()
									.getLocalName();

					myAgent.addBehaviour(new GiveProductToMarketBehaviour(interactionBehaviour, dataStore));
				}
			}
			this.setStarted(true);
		}
	}
}
