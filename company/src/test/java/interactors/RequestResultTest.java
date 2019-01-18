package interactors;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RequestResultTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	RequestResult testable;

	OrderDataStore orderDataStore_mock;

	@Before
	public void setUp() {
		orderDataStore_mock = context.mock(OrderDataStore.class);

		testable = new RequestResult(orderDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void setup() {
	}
}
