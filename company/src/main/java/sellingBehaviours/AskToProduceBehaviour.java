package sellingBehaviours;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class AskToProduceBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -6365251601845699295L;

	private String orderText;

	private AgentDataStore dataStore;

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	public AskToProduceBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		orderText = Order.fromJson(dataStore.getSubMessage().getContent()).getTextOfOrder();

		msgObj = new MessageObject("AgentSelling", orderText + " is in production");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		myAgent.addBehaviour(new AskToProduceInitiatorBehaviour(interactionBehaviour, dataStore));
	}
}
