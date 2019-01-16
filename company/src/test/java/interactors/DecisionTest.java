package interactors;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DecisionTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ResponderBehaviour responderBehaviour_mock;

	OrderDataStore orderDataStore_mock;

	Decision testable;

	@Before
	public void setUp() {
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
