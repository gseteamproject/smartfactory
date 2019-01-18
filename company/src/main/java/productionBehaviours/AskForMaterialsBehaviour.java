package productionBehaviours;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class AskForMaterialsBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 8495802171064457305L;
	private String materialsToRequest;
	private String orderText;
	private OrderDataStore dataStore;
	private ResponderBehaviour interactionBehaviour;
	private MessageObject msgObj;

	public AskForMaterialsBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
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
		Communication.server.sendMessageToClient(msgObj);
		/*
		 * System.out.
		 * println("ProductionAgent: Asking ProcurementAgent to get materials for " +
		 * orderText);
		 */
		myAgent.addBehaviour(new AskForMaterialsInitiatorBehaviour(interactionBehaviour, dataStore));
	}
}
