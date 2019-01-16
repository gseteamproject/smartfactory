package procurementBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicAgents.ProcurementAgent;
import interactors.OrderDataStore;

public class AskForAuctionInitiatorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AskForAuctionInitiator testable;

	ProcurementResponder responderBehaviour_mock;

	OrderDataStore dataStore_mock;

	ProcurementAgent agent_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ProcurementResponder.class);
		dataStore_mock = context.mock(OrderDataStore.class);
		agent_mock = context.mock(ProcurementAgent.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getThisAgent();
				will(returnValue(agent_mock));
			}
		});

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
