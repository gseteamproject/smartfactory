package communication;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ServiceException;

public class ServerServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ServerService testable;

	Server server_mock;

	ServerServiceHelper helper_mock;

	@Before
	public void setUp() {
		server_mock = context.mock(Server.class);
		helper_mock = context.mock(ServerServiceHelper.class);

		testable = new ServerService(server_mock, helper_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getName() {
		Assert.assertEquals("ServerService", testable.getName());
	}

	@Test
	public void getHelper() {
		final Agent agent_mock = context.mock(Agent.class);

		Assert.assertEquals(helper_mock, testable.getHelper(agent_mock));
	}

	@Test
	public void boot() throws ServiceException {
		final Profile profile_mock = context.mock(Profile.class);

		context.checking(new Expectations() {
			{
				oneOf(profile_mock).getParameter("server-delayTime", "0");
				will(returnValue("12345"));

				oneOf(server_mock).setDelayTime(12345);

				oneOf(server_mock).start();
			}
		});

		testable.boot(profile_mock);
	}

	@Test
	public void shutdown() {
		context.checking(new Expectations() {
			{
				oneOf(server_mock).stop();
			}
		});

		testable.shutdown();
	}
}
