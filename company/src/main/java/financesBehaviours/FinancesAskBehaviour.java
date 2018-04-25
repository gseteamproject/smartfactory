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

    public FinancesAskBehaviour(FinancesResponder interactionBehaviour, FinancesRequestResult interactor,
            OrderDataStore dataStore) {
        super(interactionBehaviour, interactor, dataStore);
    }

    @Override
    public void action() {
        ACLMessage request = interactionBehaviour.getRequest();
        Order order = Order.gson.fromJson(request.getContent(), Order.class);
        String orderText = order.getTextOfOrder();

        if (order.searchInList(SalesMarket.orderQueue) > -1) {
            if (request.getConversationId() == "Order") {
                if (!this.isStarted) {
                    this.interactor.isDone = false;
                    msgObj = new MessageObject(request, " has accepted selling of " + orderText);
                    Communication.server.sendMessageToClient(msgObj);
                    System.out.println("Finances: Order");

                    SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour
                            .getAgent().getLocalName();

                    myAgent.addBehaviour(new TransferMoneyToBank((FinancesResponder) interactionBehaviour, dataStore));
                    // myAgent.addBehaviour(new FinancesActivityBehaviour((FinancesResponder)
                    // interactionBehaviour, (FinancesRequestResult) interactor, dataStore));
                }
                this.isStarted = true;
            } else if (request.getConversationId() == "Materials") {
                if (this.isStarted) {
                    this.interactor.isDone = false;
                    msgObj = new MessageObject(request, "has accepted buying of " + orderText);
                    Communication.server.sendMessageToClient(msgObj);
                    System.out.println("Finances: Material");

                    SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour
                            .getAgent().getLocalName();

                    myAgent.addBehaviour(
                            new TransferMoneyFromBank((FinancesResponder) interactionBehaviour, dataStore));
                    // myAgent.addBehaviour(new FinancesActivityBehaviour((FinancesResponder)
                    // interactionBehaviour, (FinancesRequestResult) interactor, dataStore));
                }
                this.isStarted = false;
            }
        }
    }
}
