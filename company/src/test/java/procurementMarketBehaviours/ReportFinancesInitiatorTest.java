package procurementMarketBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class ReportFinancesInitiatorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ReportFinancesInitiator testable;

	ResponderBehaviour interactionBehaviour_mock;

	OrderDataStore dataStore_mock;

	@Before
	public void setUp() {
		interactionBehaviour_mock = context.mock(ResponderBehaviour.class);
		dataStore_mock = context.mock(OrderDataStore.class);

		testable = new ReportFinancesInitiator(interactionBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
	}
}
