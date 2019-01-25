package interactors;

import java.util.Vector;

import common.AgentDataStore;
import jade.lang.acl.ACLMessage;

public abstract class AchieveREInitiatorInteractor extends RequestInteractor {

	public AchieveREInitiatorInteractor(AgentDataStore dataStore) {
		super(dataStore);
	}

	public abstract Vector<ACLMessage> prepareRequests(ACLMessage request);

	public abstract void handleAgree(ACLMessage agree);

	public abstract void handleRefuse(ACLMessage refuse);

	public abstract void handleInform(ACLMessage inform);

	public abstract void handleFailure(ACLMessage failure);

	public abstract int next();
}
