package procurementBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import common.AgentDataStore;
import interactors.AskBehaviour;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class ProcurementAskBehaviour extends AskBehaviour {

	private static final long serialVersionUID = -4443443755165652310L;

	public ProcurementAskBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public void action() {
		if (!this.isStarted()) {
			ACLMessage request = interactionBehaviour.getRequest();
			Order order = Order.gson.fromJson(request.getContent(), Order.class);
			if (order.searchInList(CrossAgentData.orderQueue) > -1) {
				if (request.getConversationId() == "Materials") {
					// if (!this.isStarted()) {
					this.interactor.isDone = false;

					CrossAgentData.orderQueue
							.get(order.searchInList(CrossAgentData.orderQueue)).agent = interactionBehaviour
									.getAgent().getLocalName();

					// myAgent.addBehaviour(new ProcurementActivityBehaviour((ProcurementResponder)
					// interactionBehaviour, (ProcurementRequestResult) interactor, dataStore));
					myAgent.addBehaviour(
							new CheckMaterialStorage(interactionBehaviour, dataStore));
					// }
					// this.setStarted(true);
				} else if (request.getConversationId() == "Take") {
					// if (this.isStarted()) {
					this.interactor.isDone = false;

					CrossAgentData.orderQueue
							.get(order.searchInList(CrossAgentData.orderQueue)).agent = interactionBehaviour
									.getAgent().getLocalName();

					// myAgent.addBehaviour(new ProcurementActivityBehaviour((ProcurementResponder)
					// interactionBehaviour, (ProcurementRequestResult) interactor, dataStore));
					myAgent.addBehaviour(
							new GiveMaterialToProduction(interactionBehaviour, dataStore));
					// }
					// this.setStarted(false);
				}
			}
			this.setStarted(true);
		}
	}
}
