package interactors;

import jade.core.behaviours.OneShotBehaviour;

public class DecisionBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 5281539625153081963L;

	protected ResponderBehaviour interactionBehaviour;

	protected Decision interactor;

	public DecisionBehaviour(ResponderBehaviour interactionBehaviour, Decision interactor) {
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = interactor;
	}

	@Override
	public void action() {
		interactionBehaviour.setResponse(interactor.execute(interactionBehaviour.getRequest()));
	}
}
