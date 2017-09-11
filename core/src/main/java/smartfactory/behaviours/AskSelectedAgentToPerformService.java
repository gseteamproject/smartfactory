package smartfactory.behaviours;

import jade.core.behaviours.Behaviour;

public class AskSelectedAgentToPerformService extends ProductSubBehaviour {

	final static public int ServicePerformedSuccessfully = 0;
	final static public int ServicePerformedUnSuccessfully = 1;

	public AskSelectedAgentToPerformService(Behaviour behaviour) {
		super(behaviour);
	}

	@Override
	public void action() {
		// TODO : implementation
	}

	@Override
	public int onEnd() {
		return ServicePerformedSuccessfully;
	}

	private static final long serialVersionUID = 1797435133895300883L;
}
