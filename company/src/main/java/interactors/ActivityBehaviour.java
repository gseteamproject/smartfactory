package interactors;

import jade.core.behaviours.ParallelBehaviour;

public class ActivityBehaviour extends ParallelBehaviour {

	private static final long serialVersionUID = -2457919304318683300L;

	public ActivityBehaviour(ResponderBehaviour interactionBehaviour) {
		super(interactionBehaviour.getAgent(), WHEN_ANY);

		addSubBehaviour(interactionBehaviour.getDeadlineBehaviour());
		addSubBehaviour(interactionBehaviour.getAskBehaviour());
	}
}
