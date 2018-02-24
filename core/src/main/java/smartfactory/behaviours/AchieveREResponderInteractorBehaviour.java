package smartfactory.behaviours;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREResponder;
import smartfactory.interactors.AchieveREResponderInteractor;

public class AchieveREResponderInteractorBehaviour extends AchieveREResponder {

	private AchieveREResponderInteractor interactor;

	public AchieveREResponderInteractorBehaviour(Agent a, AchieveREResponderInteractor interactor) {
		super(a, AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST));
		this.interactor = interactor;
	}

	@Override
	protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
		return interactor.handleRequest(request);
	}

	@Override
	protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
		return interactor.prepareResultNotification(request, response);
	}

	private static final long serialVersionUID = 4618578824303954738L;
}
