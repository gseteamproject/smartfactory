package smartfactory.agents.common;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.application.IntegrationTests;
import smartfactory.utility.TestHelpers;
import smartfactory.utility.TestingAgent;
import test.common.Test;
import test.common.TestException;

public class InitializeAndShutdownTest extends Test {
	private static final long serialVersionUID = -8587645758077620733L;

	private TestingAgent testingAgent;

	@Override
	public Behaviour load(Agent tester) throws TestException {
		setTimeout(IntegrationTests.TEST_TIMEOUT);

		testingAgent = new TestingAgent(tester, "agent", getTestArgument("agentClass"));

		return new OneShotBehaviour() {
			private static final long serialVersionUID = -1242392710065493145L;

			@Override
			public void action() {
				try {
					testingAgent.start();
					TestHelpers.waitDFAgentTimeout();
					testingAgent.stop();
					passed("done");
				} catch (TestException e) {
					failed(e.getMessage());
				}
			}
		};
	}
}
