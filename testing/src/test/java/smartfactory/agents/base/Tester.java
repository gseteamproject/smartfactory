package smartfactory.agents.base;

import test.common.TestGroup;
import test.common.TesterAgent;

import smartfactory.application.IntegrationTests;

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
