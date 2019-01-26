package communication;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;

public class ServerServiceHelperImplTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ServerServiceHelperImpl testable;

	Server server_mock;

	@Before
	public void setUp() {
		server_mock = context.mock(Server.class);

		testable = new ServerServiceHelperImpl(server_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void init() {
		final Agent agent_mock = context.mock(Agent.class);

		testable.init(agent_mock);
	}

	@Test
	public void sendMessageToClient() {
		final MessageObject messageObject_mock = context.mock(MessageObject.class);

		context.checking(new Expectations() {
			{
				oneOf(server_mock).sendMessageToClient(messageObject_mock);
			}
		});

		testable.sendMessageToClient(messageObject_mock);
	}
}
