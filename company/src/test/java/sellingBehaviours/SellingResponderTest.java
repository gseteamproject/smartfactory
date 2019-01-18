package sellingBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicAgents.SellingAgent;
import interactors.OrderDataStore;
import jade.lang.acl.MessageTemplate;

public class SellingResponderTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	SellingResponder testable;

	OrderDataStore dataStore_mock;

	SellingAgent agent_mock;

	MessageTemplate messageTemplate;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(OrderDataStore.class);
		agent_mock = context.mock(SellingAgent.class);
		messageTemplate = MessageTemplate.MatchAll();

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getThisAgent();
				will(returnValue(agent_mock));
			}
		});

		testable = new SellingResponder(agent_mock, messageTemplate, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
