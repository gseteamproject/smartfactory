package smartfactory.interactors;

import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;

public interface AchieveREResponderInteractor {

	public ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException;

	public ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException;
}
