package smartfactory.behaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import smartfactory.interactors.AchieveREResponderInteractor;

public class AchieveREResponderInteractorBehaviourTest {
	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AchieveREResponderInteractor interactor_mock;

	Agent agent_mock;

	AchieveREResponderInteractorBehaviour interactorBehaviour;

	@Before
	public void setUp() {
		interactor_mock = context.mock(AchieveREResponderInteractor.class);
		agent_mock = context.mock(Agent.class);

		interactorBehaviour = new AchieveREResponderInteractorBehaviour(agent_mock, interactor_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void handleRequest() throws NotUnderstoodException, RefuseException {
		ACLMessage request_mock = context.mock(ACLMessage.class, "1");
		ACLMessage response_mock = context.mock(ACLMessage.class, "2");

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).handleRequest(request_mock);
				will(returnValue(response_mock));
			}
		});

		Assert.assertEquals(response_mock, interactorBehaviour.handleRequest(request_mock));
	}

	@Test
	public void prepareResultNotification() throws FailureException {
		ACLMessage request_mock = context.mock(ACLMessage.class, "1");
		ACLMessage response_mock = context.mock(ACLMessage.class, "2");

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).prepareResultNotification(request_mock, response_mock);
				will(returnValue(response_mock));
			}
		});

		Assert.assertEquals(response_mock, interactorBehaviour.prepareResultNotification(request_mock, response_mock));
	}
}
