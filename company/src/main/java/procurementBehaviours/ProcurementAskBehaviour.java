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

    public ProcurementAskBehaviour(ProcurementResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }

    @Override
    public void action() {
        if (!this.isStarted()) {
            ACLMessage request = interactionBehaviour.getRequest();
            Order order = Order.gson.fromJson(request.getContent(), Order.class);
            if (order.searchInList(SalesMarket.orderQueue) > -1) {
                if (request.getConversationId() == "Materials") {
                    // if (!this.isStarted()) {
                    this.interactor.isDone = false;

                    SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour
                            .getAgent().getLocalName();

                    // myAgent.addBehaviour(new ProcurementActivityBehaviour((ProcurementResponder)
                    // interactionBehaviour, (ProcurementRequestResult) interactor, dataStore));
                    myAgent.addBehaviour(
                            new CheckMaterialStorage((ProcurementResponder) interactionBehaviour, dataStore));
                    // }
                    // this.setStarted(true);
                } else if (request.getConversationId() == "Take") {
                    // if (this.isStarted()) {
                    this.interactor.isDone = false;

                    SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour
                            .getAgent().getLocalName();

                    // myAgent.addBehaviour(new ProcurementActivityBehaviour((ProcurementResponder)
                    // interactionBehaviour, (ProcurementRequestResult) interactor, dataStore));
                    myAgent.addBehaviour(
                            new GiveMaterialToProduction((ProcurementResponder) interactionBehaviour, dataStore));
                    // }
                    // this.setStarted(false);
                }
            }
            this.setStarted(true);
        }
    }
}
