package procurementBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class GiveMaterialToProduction extends OneShotBehaviour {

	private static final long serialVersionUID = -1386982676634257780L;

	private ResponderBehaviour interactionBehaviour;

	private String materialsToGive;

	private String orderText;

	private MessageObject msgObj;

	private AgentDataStore dataStore;

	public GiveMaterialToProduction(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.materialsToGive = dataStore.getRequestMessage().getContent();
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		Order order = Order.gson.fromJson(materialsToGive, Order.class);
		orderText = order.getTextOfOrder();
		msgObj = new MessageObject("AgentProcurement", "Taking " + orderText + " from materialStorage.");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		dataStore.setIsGiven(false);

		for (OrderPart orderPart : order.orderList) {
			for (int i = 0; i < orderPart.getAmount(); i++) {
				CrossAgentData.materialStorage.remove((Product) orderPart.getGood());
			}
		}

		dataStore.setIsGiven(true);
		interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
		if (CrossAgentData.procurementQueue.remove(order)) {
			MessageObject msgObj = new MessageObject(interactionBehaviour.getAgent().getLocalName(),
					order.getTextOfOrder() + " is removed from Procurement queue.");
			dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		}
	}
}