package salesMarketBehaviours;

import basicAgents.SalesMarket;
import basicClasses.Order;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class DeadlineForOrderBehaviour extends WakerBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 4784911250841926951L;

    protected ResponderBehaviour interactionBehaviour;
    protected OrderDataStore dataStore;

    public DeadlineForOrderBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore, long wakeTime) {
        super(interactionBehaviour.getAgent(), wakeTime);
        this.interactionBehaviour = interactionBehaviour;
        this.dataStore = dataStore;
    }

    @Override
    protected void onWake() {
        if (SalesMarket.orderQueue.size() > 0) {
            System.out.println(SalesMarket.orderQueue);
            System.out.println("dataStore " + dataStore.getRequestMessage().getContent());
            Order order = Order.gson.fromJson(dataStore.getRequestMessage().getContent(), Order.class);
            
            String agentName = SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent;
            ACLMessage deadlineMsg = new ACLMessage(ACLMessage.REQUEST);
            
            System.out.println("Big deadline of " + order);
            System.out.println("Order in " + agentName);
            
            deadlineMsg.setConversationId("Deadline");
            deadlineMsg.addReceiver(new AID((agentName), AID.ISLOCALNAME));
            deadlineMsg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
            
            String deadlineGson = Order.gson.toJson(order);
            
            deadlineMsg.setContent(deadlineGson);
            myAgent.send(deadlineMsg);
        }
    }

}
