package productionBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicAgents.ProductionAgent;
import interactors.OrderDataStore;
import jade.lang.acl.MessageTemplate;

public class ProductionResponderTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductionResponder testable;

	OrderDataStore dataStore_mock;

	ProductionAgent agent_mock;

	MessageTemplate messageTemplate;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(OrderDataStore.class);
		agent_mock = context.mock(ProductionAgent.class);
		messageTemplate = MessageTemplate.MatchAll();

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getThisAgent();
				will(returnValue(agent_mock));
			}
		});

		testable = new ProductionResponder(agent_mock, messageTemplate, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
