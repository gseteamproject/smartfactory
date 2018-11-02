package smartfactory.interactors;

import java.util.Vector;

import jade.lang.acl.ACLMessage;
import smartfactory.utility.AgentDataStore;

public abstract class AchieveREInitiatorInteractor extends Interactor {

	public AchieveREInitiatorInteractor(AgentDataStore agentDataStore) {
		super(agentDataStore);
	}

	public abstract Vector<ACLMessage> prepareRequests(ACLMessage request);

	public abstract void handleAgree(ACLMessage agree);

	public abstract void handleRefuse(ACLMessage refuse);

	public abstract void handleInform(ACLMessage inform);

	public abstract void handleFailure(ACLMessage failure);

	public abstract int next();
}
