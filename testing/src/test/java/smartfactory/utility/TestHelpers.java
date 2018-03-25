package smartfactory.utility;

import smartfactory.application.IntegrationTests;
import test.common.TestException;

public class TestHelpers {
	static public void waitDFAgentTimeout() throws TestException {
		try {
			Thread.sleep(IntegrationTests.DF_TIMEOUT);
		} catch (InterruptedException e) {
			throw new TestException("", e);
		}
	}
}
