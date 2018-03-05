package smartfactory.interactors.base;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.configuration.AgentConfiguration;
import smartfactory.platform.AgentPlatform;
import smartfactory.utility.AgentDataStore;

public class LaunchAgentTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentDataStore dataStore_mock;

	LaunchAgent testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);

		testable = new LaunchAgent(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		AgentConfiguration agentConfiguration_mock = context.mock(AgentConfiguration.class);
		AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getSubAgentConfiguration();
				will(returnValue(agentConfiguration_mock));

				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).startAgent(agentConfiguration_mock);
			}
		});

		testable.execute();
	}

	@Test
	public void next() {
		Assert.assertEquals(0, testable.next());
	}
}
