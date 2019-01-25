package interactors;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;

public class DecisionTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Decision testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore orderDataStore_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		orderDataStore_mock = context.mock(AgentDataStore.class);

		testable = new Decision(responderBehaviour_mock, orderDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void setup() {
	}
}
