package sellingBehaviours;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class AskFinancesBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -6365251601845699295L;

	private AgentDataStore dataStore;

	private ResponderBehaviour interactionBehaviour;

	public AskFinancesBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		String orderText = Order.fromJson(dataStore.getSubMessage().getContent()).getTextOfOrder();

		MessageObject msgObj = new MessageObject("AgentSelling", orderText + " is in finances");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		myAgent.addBehaviour(new AskFinancesInitiatorBehaviour(interactionBehaviour, dataStore));
	}
}
