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
			Order order = dataStore.getOrder();
			ACLMessage request = interactionBehaviour.getRequest();

			if (order.searchInList(CrossAgentData.orderQueue) > -1) {
				if (request.getConversationId() == "Order") {
					this.interactor.isDone = false;

					msgObj = new MessageObject(request, " has accepted selling of " + order.getTextOfOrder());
					dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

					CrossAgentData.orderQueue.get(order.searchInList(CrossAgentData.orderQueue)).agent = myAgent
							.getLocalName();

					myAgent.addBehaviour(new TransferMoneyToBank(interactionBehaviour, dataStore));
				} else if (request.getConversationId() == "Materials") {
					this.interactor.isDone = false;

					msgObj = new MessageObject(request, "has accepted buying of " + order.getTextOfOrder());
					dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

					CrossAgentData.orderQueue.get(order.searchInList(CrossAgentData.orderQueue)).agent = myAgent
							.getLocalName();

					myAgent.addBehaviour(new TransferMoneyFromBank(interactionBehaviour, dataStore));
				}
			}
			this.setStarted(true);
		}
	}
}
