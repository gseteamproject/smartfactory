package sellingBehaviours;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class AskFinancesBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -6365251601845699295L;
	private String orderText;
	private OrderDataStore dataStore;
	private ResponderBehaviour interactionBehaviour;
	private MessageObject msgObj;

	public AskFinancesBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		orderText = Order.gson.fromJson(dataStore.getSubMessage().getContent(), Order.class).getTextOfOrder();

		msgObj = new MessageObject("AgentSelling", orderText + " is in finances");
		Communication.server.sendMessageToClient(msgObj);

		myAgent.addBehaviour(new AskFinancesInitiatorBehaviour(interactionBehaviour, dataStore));
	}
}
