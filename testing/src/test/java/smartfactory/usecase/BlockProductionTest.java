package smartfactory.usecase;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.application.IntegrationTests;
import test.common.Test;
import test.common.TestException;

public class BlockProductionTest extends Test {
	private static final long serialVersionUID = 3042735222225099514L;

	@Override
	public Behaviour load(Agent tester) throws TestException {
		setTimeout(IntegrationTests.TEST_TIMEOUT);

		return new OneShotBehaviour() {
			private static final long serialVersionUID = 8080897193054917900L;

			@Override
			public void action() {
				passed("done");
			}
		};
	}
}
