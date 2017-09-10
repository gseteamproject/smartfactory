package smartfactory.behaviours;

import jade.core.behaviours.Behaviour;

public class SelectAgentToPerformService extends ProductSubBehaviour {
	
	public SelectAgentToPerformService(Behaviour behaviour) {
		super(behaviour);
	}

	final static public int AgentToPerformServiceIsSelected = 0;
	final static public int AgentToPerformServiceIsNotSelected = 1;

	@Override
	public void action() {
	}

	private static final long serialVersionUID = 4019502062871394333L;
}
