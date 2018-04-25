package productionBehaviours;

import java.util.Vector;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.OrderDataStore;
import interactors.RequestInteractor;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AskForMaterialsInitiator extends RequestInteractor implements AchieveREInitiatorInteractor {

    private ProductionResponder interactionBehaviour;
    private String orderText;
    public MessageObject msgObj;

    public AskForMaterialsInitiator(ProductionResponder interactionBehaviour, OrderDataStore dataStore) {
        super(dataStore);
        this.interactionBehaviour = interactionBehaviour;
    }

    // CPD-OFF
    // TODO : fix cpd

    @Override
    public Vector<ACLMessage> prepareRequests(ACLMessage request) {
        request = new ACLMessage(ACLMessage.REQUEST);

        String requestedAction = "Materials";
        request.setConversationId(requestedAction);
        request.addReceiver(new AID(("AgentProcurement"), AID.ISLOCALNAME));
        request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        request.setContent(dataStore.getRequestMessage().getContent());



        Vector<ACLMessage> l = new Vector<ACLMessage>(1);
        l.addElement(request);
        return l;
    }

    @Override
    public void handleAgree(ACLMessage agree) {
        // TODO Auto-generated method stub

    }

    @Override
    public void handleRefuse(ACLMessage refuse) {
        // TODO Auto-generated method stub

    }

    // CPD-ON

    @Override
    public void handleInform(ACLMessage inform) {
        // TODO Auto-generated method stub
        orderText = Order.gson.fromJson(inform.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject(inform, "received [inform] materials for " + orderText + " are in storage");
        Communication.server.sendMessageToClient(msgObj);

/*        System.out.println(msgObj.getReceivedMessage());*/

        // System.out.println("ProductionAgent: received [inform] materials for " +
        // orderText + " are in storage");
        // stop();
        interactionBehaviour.getAgent().addBehaviour(new TakeFromStorageBehaviour(interactionBehaviour, dataStore));

    }

    @Override
    public void handleFailure(ACLMessage failure) {
        // TODO Auto-generated method stub
        orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject(failure, "received [failure] materials for " + orderText + " are not in storage");
        Communication.server.sendMessageToClient(msgObj);
       /* System.out.println(msgObj.getReceivedMessage());*/

        // System.out
        // .println("ProductionAgent: received [failure] materials for " + orderText + "
        // are not in storage");

        MessageTemplate temp = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        MessageTemplate infTemp = MessageTemplate.and(temp, MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        infTemp = MessageTemplate.and(infTemp, MessageTemplate.MatchConversationId("Materials"));

    }

    @Override
    public int next() {
        // TODO Auto-generated method stub
        return 0;
    }

}
