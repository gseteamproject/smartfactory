package procurementBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;

public class AskForAuctionInitiatorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AskForAuctionInitiator testable;

	ResponderBehaviour responderBehaviour_mock;

	OrderDataStore dataStore_mock;

	Agent agent_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		dataStore_mock = context.mock(OrderDataStore.class);
		agent_mock = context.mock(Agent.class);

		testable = new AskForAuctionInitiator(responderBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void prepareRequests() {
	}
}
