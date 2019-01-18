package interactors;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class ResponderBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ResponderBehaviour testable;

	OrderDataStore orderDataStore_mock;

	Agent agent_mock;

	MessageTemplate messageTemplate;

	@Before
	public void setUp() {
		orderDataStore_mock = context.mock(OrderDataStore.class);
		agent_mock = context.mock(Agent.class);
		messageTemplate = MessageTemplate.MatchAll();

		testable = new ResponderBehaviour(agent_mock, messageTemplate, orderDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void setup() {
	}
}
