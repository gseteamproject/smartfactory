package financesBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import interactors.RequestResult;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class TransferMoneyToBankTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	TransferMoneyToBank testable;

	ResponderBehaviour responderBehaviour_mock;

	RequestResult requestResult_mock;

	ACLMessage message_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(message_mock));

				oneOf(message_mock).getContent();
				will(returnValue("content"));
			}
		});

		testable = new TransferMoneyToBank(responderBehaviour_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
	}
}
