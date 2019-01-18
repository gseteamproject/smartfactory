package procurementBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicAgents.ProcurementAgent;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class CheckMaterialStorageTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	CheckMaterialStorage testable;

	ResponderBehaviour responderBehaviour_mock;

	OrderDataStore dataStore_mock;

	ProcurementAgent agent_mock;

	ACLMessage message_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		dataStore_mock = context.mock(OrderDataStore.class);
		agent_mock = context.mock(ProcurementAgent.class);
		message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(message_mock));

				oneOf(message_mock).getContent();
				will(returnValue("request-content"));

				oneOf(dataStore_mock).getThisAgent();
				will(returnValue(agent_mock));
			}
		});

		testable = new CheckMaterialStorage(responderBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
	}
}
