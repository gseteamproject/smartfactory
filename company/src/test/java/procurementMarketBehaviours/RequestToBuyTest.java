package procurementMarketBehaviours;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicClasses.OrderPart;
import jade.core.AID;

public class RequestToBuyTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	RequestToBuy testable;

	List<AID> procurementAgents;

	OrderPart orderPart_mock;

	@Before
	public void setUp() {
		procurementAgents = new ArrayList<AID>();
		orderPart_mock = context.mock(OrderPart.class);

		testable = new RequestToBuy(procurementAgents, orderPart_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
	}
}
