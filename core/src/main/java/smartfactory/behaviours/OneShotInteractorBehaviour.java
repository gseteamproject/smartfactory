package smartfactory.behaviours;

import jade.core.behaviours.OneShotBehaviour;
import smartfactory.interactors.OneShotInteractor;

public class OneShotInteractorBehaviour extends OneShotBehaviour {

	private OneShotInteractor interactor;

	public OneShotInteractorBehaviour(OneShotInteractor interactor) {
		this.interactor = interactor;
	}

	@Override
	public void action() {
		interactor.execute();
	}

	@Override
	public int onEnd() {
		return interactor.next();
	}

	private static final long serialVersionUID = 6376319254003814742L;
}
