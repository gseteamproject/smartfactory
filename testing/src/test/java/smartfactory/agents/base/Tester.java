package smartfactory.agents.base;

import smartfactory.application.IntegrationTests;
import test.common.TestGroup;
import test.common.TesterAgent;

public class Tester extends TesterAgent {
	private static final long serialVersionUID = 2890828714489116537L;

	@Override
	protected TestGroup getTestGroup() {
		return new TestGroup("smartfactory/agents/base/tests.xml");
	}

	public static void main(String[] args) {
		IntegrationTests.launchDedicatedTester(Tester.class, args);
	}
}
