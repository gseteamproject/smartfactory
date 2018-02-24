package smartfactory.behaviours;

import java.util.Vector;

import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import smartfactory.interactors.AchieveREInitiatorInteractor;

public class AchieveREInitiatorInteractorBehaviour extends AchieveREInitiator {

	private AchieveREInitiatorInteractor interactor;

	public AchieveREInitiatorInteractorBehaviour(AchieveREInitiatorInteractor interactor) {
		super(null, null);
		this.interactor = interactor;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Vector prepareRequests(ACLMessage request) {
		return interactor.prepareRequests(request);
	}

	@Override
	protected void handleAgree(ACLMessage agree) {
		interactor.handleAgree(agree);
	}

	@Override
	protected void handleRefuse(ACLMessage refuse) {
		interactor.handleRefuse(refuse);
	}

	@Override
	protected void handleInform(ACLMessage inform) {
		interactor.handleInform(inform);
	}

	@Override
	protected void handleFailure(ACLMessage failure) {
		interactor.handleFailure(failure);
	}

	@Override
	public int onEnd() {
		return interactor.next();
	}

	private static final long serialVersionUID = 4025617324638817166L;
}
