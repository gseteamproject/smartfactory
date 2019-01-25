package productionBehaviours;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class AskForMaterialsBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 8495802171064457305L;

	private String materialsToRequest;

	private String orderText;

	private AgentDataStore dataStore;

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	public AskForMaterialsBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		materialsToRequest = interactionBehaviour.getRequest().getContent();
		orderText = Order.gson.fromJson(materialsToRequest, Order.class).getTextOfOrder();
		dataStore.setRequestMessage(interactionBehaviour.getRequest());

		msgObj = new MessageObject("AgentProduction", "Asking ProcurementAgent to get materials for " + orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		myAgent.addBehaviour(new AskForMaterialsInitiatorBehaviour(interactionBehaviour, dataStore));
	}
}
