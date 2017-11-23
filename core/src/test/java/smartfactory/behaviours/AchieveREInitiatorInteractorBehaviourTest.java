package smartfactory.behaviours;

import java.util.Vector;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.lang.acl.ACLMessage;
import smartfactory.interactors.AchieveREInitiatorInteractor;

public class AchieveREInitiatorInteractorBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AchieveREInitiatorInteractor interactor_mock;

	AchieveREInitiatorInteractorBehaviour testable;

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
		ACLMessage message_mock = context.mock(ACLMessage.class);
		Vector<?> vector_mock = context.mock(Vector.class);

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).prepareRequests(message_mock);
				will(returnValue(vector_mock));
			}
		});

		Assert.assertEquals(vector_mock, testable.prepareRequests(message_mock));
	}

	@Test
	public void handleInform() {
		ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).handleInform(message_mock);
			}
		});

		testable.handleInform(message_mock);
	}

	@Test
	public void handleRefuse() {
		ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).handleRefuse(message_mock);
			}
		});

		testable.handleRefuse(message_mock);
	}

	@Test
	public void handleAgree() {
		ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).handleAgree(message_mock);
			}
		});

		testable.handleAgree(message_mock);
	}

	@Test
	public void handleFailure() {
		ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).handleFailure(message_mock);
			}
		});

		testable.handleFailure(message_mock);
	}

	@Test
	public void onEnd() {
		final int transition = 1;

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).next();
				will(returnValue(transition));
			}
		});

		Assert.assertEquals(transition, testable.onEnd());
	}
}
