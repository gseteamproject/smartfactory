package interactors;

import java.util.Vector;

import jade.lang.acl.ACLMessage;

public interface AchieveREInitiatorInteractor {

    public Vector<ACLMessage> prepareRequests(ACLMessage request);

    public void handleAgree(ACLMessage agree);

    public void handleRefuse(ACLMessage refuse);

    public void handleInform(ACLMessage inform);

    public void handleFailure(ACLMessage failure);

    public int next();
}