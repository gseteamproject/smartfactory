package sellingBehaviours;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class AskToProduceBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -6365251601845699295L;
	private String orderText;
	private OrderDataStore dataStore;
	private ResponderBehaviour interactionBehaviour;
	private MessageObject msgObj;

	public AskToProduceBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		orderText = Order.gson.fromJson(dataStore.getSubMessage().getContent(), Order.class).getTextOfOrder();

		msgObj = new MessageObject("AgentSelling", orderText + " is in production");
		Communication.server.sendMessageToClient(msgObj);

		myAgent.addBehaviour(new AskToProduceInitiatorBehaviour(interactionBehaviour, dataStore));
	}
}
