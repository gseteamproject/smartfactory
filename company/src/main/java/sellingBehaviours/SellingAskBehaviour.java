package sellingBehaviours;

import basicAgents.SalesMarket;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.AskBehaviour;
import interactors.OrderDataStore;
import jade.lang.acl.ACLMessage;

public class SellingAskBehaviour extends AskBehaviour {

    private static final long serialVersionUID = -4443443755165652310L;
    private MessageObject msgObj;

    public SellingAskBehaviour(SellingResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }

    @Override
    public void action() {
        if (!this.isStarted()) {
            // ACLMessage request = dataStore.getRequestMessage();
            ACLMessage request = interactionBehaviour.getRequest();
            Order order = Order.gson.fromJson(request.getContent(), Order.class);
            String orderText = order.getTextOfOrder();

            if (order.searchInList(SalesMarket.orderQueue) > -1) {
                if (request.getConversationId() == "Ask") {
                    // if (!this.isStarted()) {
                    this.interactor.isDone = false;
                    msgObj = new MessageObject(request, "will check warehouse for " + orderText);
                    Communication.server.sendMessageToClient(msgObj);

                    // myAgent.addBehaviour(new SellingActivityBehaviour((SellingResponder)
                    // interactionBehaviour, (SellingRequestResult) interactor, dataStore));
                    // myAgent.addBehaviour(new SellingDeadlineBehaviour((SellingResponder)
                    // interactionBehaviour, (SellingRequestResult) interactor, dataStore));

                    SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour
                            .getAgent().getLocalName();

                    /*
                     * System.out.println(msgObj.getReceivedMessage());
                     * Communication.server.sendMessageToClient("SellingAgent",
                     * "[agree] I will check warehouse for " + orderText);
                     */
                    myAgent.addBehaviour(
                            new CheckWarehouseBehaviour((SellingResponder) interactionBehaviour, dataStore));
                    // }
                    // this.setStarted(true);
                } else if (request.getConversationId() == "Take") {

                    // if (this.isStarted()) {
                    this.interactor.isDone = false;
                    msgObj = new MessageObject(request, "will give order " + orderText + " from warehouse");
                    Communication.server.sendMessageToClient(msgObj);

                    // myAgent.addBehaviour(new SellingActivityBehaviour((SellingResponder)
                    // interactionBehaviour, (SellingRequestResult) interactor, dataStore));
                    // myAgent.addBehaviour(new SellingDeadlineBehaviour((SellingResponder)
                    // interactionBehaviour, (SellingRequestResult) interactor, dataStore));

                    SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour
                            .getAgent().getLocalName();

                    /*
                     * System.out.println(msgObj.getReceivedMessage());
                     * Communication.server.sendMessageToClient("SellingAgent",
                     * "[agree] I will give you " + orderText + " from warehouse");
                     */
                    myAgent.addBehaviour(
                            new GiveProductToMarketBehaviour((SellingResponder) interactionBehaviour, dataStore));
                    // }
                    // this.setStarted(false);
                }
            }
            this.setStarted(true);
        }
    }
}
