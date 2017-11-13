package smartfactory.behaviours.machine;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.MachineDataStore;

public class DecisionBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ActivityResponderBehaviour interactionBehaviour_mock;

	MachineDataStore dataStore_mock;

	DecisionBehaviour testable;

	@Before
	public void setUp() {
		interactionBehaviour_mock = context.mock(ActivityResponderBehaviour.class);
		dataStore_mock = context.mock(MachineDataStore.class);

		testable = new DecisionBehaviour(interactionBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
