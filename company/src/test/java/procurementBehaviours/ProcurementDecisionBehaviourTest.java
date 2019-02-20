package procurementBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import interactors.ResponderBehaviour;

public class ProcurementDecisionBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcurementDecisionBehaviour testable;

	AgentDataStore agentDataStore_mock;

	ResponderBehaviour responderBehaviour_mock;

	@Before
	public void setUp() {
		agentDataStore_mock = context.mock(AgentDataStore.class);
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);

		testable = new ProcurementDecisionBehaviour(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
