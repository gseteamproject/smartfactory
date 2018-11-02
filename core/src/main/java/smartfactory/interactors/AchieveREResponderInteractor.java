package smartfactory.interactors;

import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import smartfactory.utility.AgentDataStore;

public abstract class AchieveREResponderInteractor extends Interactor {

	public AchieveREResponderInteractor(AgentDataStore agentDataStore) {
		super(agentDataStore);
	}

	public abstract ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException;

	public abstract ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException;
}
