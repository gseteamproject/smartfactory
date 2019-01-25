package sellingBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;

public class SellingRequestResultTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	SellingRequestResult testable;

	AgentDataStore dataStore_mock;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);

		testable = new SellingRequestResult(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
	}
}
