package smartfactory.agents.base;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import test.common.Test;
import test.common.TestException;

public class LifecycleStates extends Test {

	private static final long serialVersionUID = 1658372934317164405L;

	@Override
	public void clean(Agent arg0) {
		super.clean(arg0);
	}

	@Override
	public Behaviour load(Agent arg0) throws TestException {

		OneShotBehaviour b = new OneShotBehaviour() {

			private static final long serialVersionUID = -1242392710065493145L;

			@Override
			public void action() {
				passed("done");
			}
		};

		return b;
	}
}
