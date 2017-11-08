package smartfactory.behaviours.machine;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import smartfactory.models.Machine;

public class Status extends TickerBehaviour {

	public Status(Agent a) {
		super(a, 500);
	}

	@Override
	protected void onTick() {
		Activity parent = (Activity) getParent();
		Machine machine = parent.getMachine();
		
		machine.getStatus();
	}

	private static final long serialVersionUID = 8310293249196722957L;
}
