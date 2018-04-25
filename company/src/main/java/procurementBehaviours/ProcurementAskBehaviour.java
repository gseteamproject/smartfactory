package procurementBehaviours;

import basicAgents.SalesMarket;
import basicClasses.Order;
import interactors.AskBehaviour;
import interactors.OrderDataStore;
import jade.lang.acl.ACLMessage;

public class ProcurementAskBehaviour extends AskBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -4443443755165652310L;

    public ProcurementAskBehaviour(ProcurementResponder interactionBehaviour, ProcurementRequestResult interactor,
            OrderDataStore dataStore) {
        super(interactionBehaviour, interactor, dataStore);
    }

    @Override
    public void action() {
        ACLMessage request = interactionBehaviour.getRequest();
        Order order = Order.gson.fromJson(interactionBehaviour.getRequest().getContent(), Order.class);
        if (order.searchInList(SalesMarket.orderQueue) > -1) {
            if (request.getConversationId() == "Materials") {
                if (!this.isStarted) {
                    this.interactor.isDone = false;

                    SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour
                            .getAgent().getLocalName();

                    // myAgent.addBehaviour(new ProcurementActivityBehaviour((ProcurementResponder)
                    // interactionBehaviour, (ProcurementRequestResult) interactor, dataStore));
                    myAgent.addBehaviour(
                            new CheckMaterialStorage((ProcurementResponder) interactionBehaviour, dataStore));
                }
                this.isStarted = true;
            } else if (request.getConversationId() == "Take") {
                if (this.isStarted) {
                    this.interactor.isDone = false;

                    SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour
                            .getAgent().getLocalName();

                    // myAgent.addBehaviour(new ProcurementActivityBehaviour((ProcurementResponder)
                    // interactionBehaviour, (ProcurementRequestResult) interactor, dataStore));
                    myAgent.addBehaviour(
                            new GiveMaterialToProduction((ProcurementResponder) interactionBehaviour, dataStore));
                }
                this.isStarted = false;
            }
        }
    }
}
