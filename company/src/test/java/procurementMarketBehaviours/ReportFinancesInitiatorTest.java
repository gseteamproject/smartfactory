package procurementMarketBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import interactors.ResponderBehaviour;

public class ReportFinancesInitiatorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ReportFinancesInitiator testable;

	ResponderBehaviour interactionBehaviour_mock;

	AgentDataStore dataStore_mock;

	@Before
	public void setUp() {
		interactionBehaviour_mock = context.mock(ResponderBehaviour.class);
		dataStore_mock = context.mock(AgentDataStore.class);

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
