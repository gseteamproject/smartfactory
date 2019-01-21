package procurementMarketBehaviours;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicClasses.OrderPart;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.AID;

public class RequestToBuyTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	RequestToBuy testable;

	List<AID> procurementAgents;

	ResponderBehaviour interactionBehaviour_mock;

	OrderPart orderPart_mock;

	OrderDataStore dataStore_mock;

	@Before
	public void setUp() {
		procurementAgents = new ArrayList<AID>();
		interactionBehaviour_mock = context.mock(ResponderBehaviour.class);
		orderPart_mock = context.mock(OrderPart.class);
		dataStore_mock = context.mock(OrderDataStore.class);

		testable = new RequestToBuy(procurementAgents, interactionBehaviour_mock, orderPart_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
	}
}
