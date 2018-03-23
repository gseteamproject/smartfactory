package smartfactory.agents.process;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.application.IntegrationTests;
import smartfactory.configuration.AgentConfiguration;
import test.common.Test;
import test.common.TestException;
import test.common.TestUtility;

public class LifecycleStates extends Test {
	private static final long serialVersionUID = -5127229265640107059L;

	@Override
	public Behaviour load(Agent tester) throws TestException {
		setTimeout(IntegrationTests.TEST_TIMEOUT);

		return new OneShotBehaviour() {
			private static final long serialVersionUID = 3146063293714406761L;

			private AID agent;

			@Override
			public void action() {
				try {
					startAgent();
					waitDFAgent();
					stopAgent();
					passed("done");
				} catch (TestException e) {
					failed(e.getMessage());
				}
			}

			private void startAgent() throws TestException {
				agent = TestUtility.createAgent(tester, "process", smartfactory.agents.ProcessAgent.class.getName(),
						new AgentConfiguration().getAgentParameters());
			}

			private void stopAgent() throws TestException {
				TestUtility.killAgent(tester, agent);
			}

			private void waitDFAgent() {
				try {
					Thread.sleep(IntegrationTests.DF_TIMEOUT);
				} catch (InterruptedException e) {
					failed(e.getMessage());
				}
			}
		};
	}
}
