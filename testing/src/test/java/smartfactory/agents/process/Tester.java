package smartfactory.agents.process;

import smartfactory.application.IntegrationTests;
import test.common.TestGroup;
import test.common.TesterAgent;

public class Tester extends TesterAgent {
	private static final long serialVersionUID = -3117267002824169763L;

	@Override
	protected TestGroup getTestGroup() {
		return new TestGroup("smartfactory/agents/process/tests.xml");
	}

	public static void main(String[] args) {
		IntegrationTests.launchDedicatedTester(Tester.class, args);
	}
}
