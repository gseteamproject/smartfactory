package procurementMarketBehaviours;

import java.util.List;

import basicAgents.Procurement;
import basicClasses.OrderPart;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/* possible states of request */
enum RequestState {
    PREPARE_CALL_FOR_PROPOSAL, HANDLE_CALL_FOR_PROPOSAL_REPLY, PREPARE_ACCEPT_PROPOSAL, HANDLE_ACCEPT_PROPOSAL_REPLY, DONE
};

public class RequestToBuy extends SimpleBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -1322936877118129497L;

    private ProcurementMarketResponder interactionBehaviour;
    private OrderDataStore dataStore;
    private MessageObject msgObj;
    public static int buyCount;

    List<AID> procurementAgents;
    RequestState requestState;
    OrderPart currentOrder;

    public RequestToBuy(List<AID> procurementAgents, ProcurementMarketResponder interactionBehaviour,
            OrderDataStore dataStore, OrderPart currentOrder) {
        this.procurementAgents = procurementAgents;
        /* initial state for behaviour */
        this.requestState = RequestState.PREPARE_CALL_FOR_PROPOSAL;
        this.currentOrder = currentOrder;
        this.interactionBehaviour = interactionBehaviour;
        this.dataStore = dataStore;
    }

    MessageTemplate replyTemplate = null;
    int repliesLeft = 0;

    AID bestPrinterAgent = null;
    int bestPrice = 0;

    @Override
    public void action() {
        ACLMessage msg = null;

        /* perform actions accordingly to behaviour state */
        switch (requestState) {
        case PREPARE_CALL_FOR_PROPOSAL:
            msg = new ACLMessage(ACLMessage.CFP);
            for (AID agentProvidingService : procurementAgents) {
                msg.addReceiver(agentProvidingService);
            }
            msg.setConversationId("buying");
            msg.setContent("material");
            msg.setReplyWith(String.valueOf(System.currentTimeMillis()));

            replyTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId("buying"),
                    MessageTemplate.MatchInReplyTo(msg.getReplyWith()));
            repliesLeft = procurementAgents.size();
            myAgent.send(msg);

            requestState = RequestState.HANDLE_CALL_FOR_PROPOSAL_REPLY;
            break;

        case HANDLE_CALL_FOR_PROPOSAL_REPLY:
            msg = myAgent.receive(replyTemplate);
            if (msg != null) {
                int price = Integer.parseInt(msg.getContent());
                if (bestPrinterAgent == null || price < bestPrice) {
                    bestPrinterAgent = msg.getSender();
                    bestPrice = price;
                }
                repliesLeft--;
                if (repliesLeft == 0) {
                    requestState = RequestState.PREPARE_ACCEPT_PROPOSAL;
                }
            } else {
                block();
            }
            break;

        case PREPARE_ACCEPT_PROPOSAL:
            msg = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
            msg.addReceiver(bestPrinterAgent);
            msg.setConversationId("buying");
            msg.setContent("material");
            msg.setReplyWith(String.valueOf(System.currentTimeMillis()));

            replyTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId("buying"),
                    MessageTemplate.MatchInReplyTo(msg.getReplyWith()));
            repliesLeft = 1;

            myAgent.send(msg);

            requestState = RequestState.HANDLE_ACCEPT_PROPOSAL_REPLY;
            break;

        case HANDLE_ACCEPT_PROPOSAL_REPLY:
            msg = myAgent.receive(replyTemplate);
            if (msg != null) {

                msgObj = new MessageObject("AgentProcurementMarket",
                        currentOrder.getPart().getClass().getSimpleName() + " is found with " + bestPrice);
                Communication.server.sendMessageToClient(msgObj);

                /*
                 * System.out.println(String
                 * .format(currentOrder.getPart().getClass().getSimpleName() +
                 * " is found (price=%d)", bestPrice));
                 */

                repliesLeft = 0;
                requestState = RequestState.DONE;
                Procurement.materialStorage.add(currentOrder.getPart());
                buyCount += 1;
            } else {
                block();
            }
            break;

        case DONE:
            break;

        default:
            break;
        }
    }

    @Override
    public boolean done() {
        /* behaviour is finished when it reaches DONE state */
        if (buyCount == AuctionInitiator.partsCount) {
            dataStore.getRequestResult().execute(interactionBehaviour.getRequest());
        }
        return requestState == RequestState.DONE;
    }
}