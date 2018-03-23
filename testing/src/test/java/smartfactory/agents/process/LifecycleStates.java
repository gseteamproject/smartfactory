package smartfactory.agents.process;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.application.IntegrationTests;
import test.common.Test;
import test.common.TestException;

public class LifecycleStates extends Test {

	private static final long serialVersionUID = -5127229265640107059L;

	@Override
	public Behaviour load(Agent tester) throws TestException {

		setTimeout(IntegrationTests.TIMEOUT);

		return new OneShotBehaviour() {

			private static final long serialVersionUID = 3146063293714406761L;

			@Override
			public void action() {
				passed("done");
			}
		};
	}
}
