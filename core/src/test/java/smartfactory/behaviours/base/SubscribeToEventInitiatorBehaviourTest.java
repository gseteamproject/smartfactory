package smartfactory.behaviours.base;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smartfactory.models.EventHandler;

public class SubscribeToEventInitiatorBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	SubscribeToEventInitiatorBehaviour testable;

	EventHandler callback_mock;

	String responderAgent;

	String event;

	@Before
	public void setUp() {
		responderAgent = "responderAgent";
		event = "event";
		callback_mock = context.mock(EventHandler.class);

		testable = new SubscribeToEventInitiatorBehaviour(responderAgent, event, callback_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
