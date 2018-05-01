package financesBehaviours;

import basicAgents.SalesMarket;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.AskBehaviour;
import interactors.OrderDataStore;
import jade.lang.acl.ACLMessage;

public class FinancesAskBehaviour extends AskBehaviour {

    private static final long serialVersionUID = 2249201124835167657L;
    private MessageObject msgObj;

    public FinancesAskBehaviour(FinancesResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }

    @Override
    public void action() {
        if (!this.isStarted()) {
            ACLMessage request = interactionBehaviour.getRequest();
            Order order = Order.gson.fromJson(request.getContent(), Order.class);
            String orderText = order.getTextOfOrder();

            if (order.searchInList(SalesMarket.orderQueue) > -1) {
                if (request.getConversationId() == "Order") {
                    // if (!this.isStarted()) {
                    this.interactor.isDone = false;
                    msgObj = new MessageObject(request, " has accepted selling of " + orderText);
                    Communication.server.sendMessageToClient(msgObj);

                    SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour
                            .getAgent().getLocalName();

                    myAgent.addBehaviour(new TransferMoneyToBank((FinancesResponder) interactionBehaviour, dataStore));
                    // myAgent.addBehaviour(new FinancesActivityBehaviour((FinancesResponder)
                    // interactionBehaviour, (FinancesRequestResult) interactor, dataStore));
                    // }
                    // this.setStarted(true);
                } else if (request.getConversationId() == "Materials") {
                    // if (this.isStarted()) {
                    this.interactor.isDone = false;
                    msgObj = new MessageObject(request, "has accepted buying of " + orderText);
                    Communication.server.sendMessageToClient(msgObj);

                    SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour
                            .getAgent().getLocalName();

                    myAgent.addBehaviour(
                            new TransferMoneyFromBank((FinancesResponder) interactionBehaviour, dataStore));
                    // myAgent.addBehaviour(new FinancesActivityBehaviour((FinancesResponder)
                    // interactionBehaviour, (FinancesRequestResult) interactor, dataStore));
                    // }
                    // this.setStarted(false);
                }
            }
            this.setStarted(true);
        }
    }
}
