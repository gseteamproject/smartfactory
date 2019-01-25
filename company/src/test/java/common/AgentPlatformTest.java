package common;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import communication.MessageObject;
import communication.WebServerHelper;
import communication.WebServerService;
import jade.core.Agent;
import jade.core.ServiceException;

public class AgentPlatformTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentPlatform testable;

	Agent agent_mock;

	@Before
	public void setUp() {
		agent_mock = context.mock(Agent.class);

		testable = new AgentPlatform(agent_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void sendMessageToWebClient() throws ServiceException {
		final MessageObject message_mock = context.mock(MessageObject.class);
		final WebServerHelper helper_mock = context.mock(WebServerHelper.class);

		context.checking(new Expectations() {
			{
				oneOf(agent_mock).getHelper(WebServerService.NAME);
				will(returnValue(helper_mock));

				oneOf(helper_mock).sendMessageToClient(message_mock);
			}
		});

		testable.sendMessageToWebClient(message_mock);
	}
}
