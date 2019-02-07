package interactors;

import java.util.Vector;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.lang.acl.ACLMessage;

public class AchieveREInitiatorInteractorBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AchieveREInitiatorInteractorBehaviour testable;

	AchieveREInitiatorInteractor interactor_mock;

	@Before
	public void setUp() {
		interactor_mock = context.mock(AchieveREInitiatorInteractor.class);

		testable = new AchieveREInitiatorInteractorBehaviour(interactor_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void prepareRequests() {
		final ACLMessage message_mock = context.mock(ACLMessage.class);
		final Vector<?> vector = new Vector<ACLMessage>();

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).prepareRequests(message_mock);
				will(returnValue(vector));
			}
		});

		Assert.assertEquals(vector, testable.prepareRequests(message_mock));
	}

	@Test
	public void handleAgree() {
		final ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).handleAgree(message_mock);
			}
		});

		testable.handleAgree(message_mock);
	}

	@Test
	public void handleRefuse() {
		final ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).handleRefuse(message_mock);
			}
		});

		testable.handleRefuse(message_mock);
	}

	@Test
	public void handleInform() {
		final ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).handleInform(message_mock);
			}
		});

		testable.handleInform(message_mock);
	}

	@Test
	public void handleFailure() {
		final ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).handleFailure(message_mock);
			}
		});

		testable.handleFailure(message_mock);
	}

	@Test
	public void onEnd() {
		final int result = 1;

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).next();
				will(returnValue(result));
			}
		});

		Assert.assertEquals(result, testable.onEnd());
	}
}
