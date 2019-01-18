package procurementMarketBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicAgents.ProcurementMarketAgent;
import interactors.OrderDataStore;
import jade.lang.acl.MessageTemplate;

public class ProcurementMarketResponderTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcurementMarketResponder testable;

	OrderDataStore dataStore_mock;

	ProcurementMarketAgent agent_mock;

	MessageTemplate messageTemplate;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(OrderDataStore.class);
		agent_mock = context.mock(ProcurementMarketAgent.class);
		messageTemplate = MessageTemplate.MatchAll();

		testable = new ProcurementMarketResponder(agent_mock, messageTemplate, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
