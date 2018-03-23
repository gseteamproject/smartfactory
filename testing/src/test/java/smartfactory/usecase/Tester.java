package smartfactory.usecase;

import smartfactory.application.IntegrationTests;
import test.common.TestGroup;
import test.common.TesterAgent;

public class Tester extends TesterAgent {
	private static final long serialVersionUID = 5767054729282628020L;

	@Override
	protected TestGroup getTestGroup() {
		return new TestGroup("smartfactory/usecase/tests.xml");
	}

	public static void main(String[] args) {
		IntegrationTests.launchDedicatedTester(Tester.class, args);
	}
}
