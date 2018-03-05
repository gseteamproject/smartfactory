package smartfactory.container;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smartfactory.configuration.AgentConfiguration;
import smartfactory.configuration.Configuration;
import smartfactory.configuration.ContainerConfiguration;
import smartfactory.platform.AgentPlatform;

public class ContainerTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentPlatform agentPlatform_mock;

	Container testable;

	@Before
	public void setUp() {
		agentPlatform_mock = context.mock(AgentPlatform.class);

		testable = new Container(agentPlatform_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void launch() {
		Configuration configuration_mock = context.mock(Configuration.class);
		ContainerConfiguration containerConfiguration_mock = context.mock(ContainerConfiguration.class);
		AgentConfiguration agentConfiguration_mock = context.mock(AgentConfiguration.class, "agent-configuration");
		List<AgentConfiguration> agentConfigurations = new ArrayList<AgentConfiguration>();
		agentConfigurations.add(agentConfiguration_mock);

		context.checking(new Expectations() {
			{
				oneOf(configuration_mock).getContainerConfiguration();
				will(returnValue(containerConfiguration_mock));

				oneOf(agentPlatform_mock).startContainer(containerConfiguration_mock);

				oneOf(configuration_mock).getAgentConfigurations();
				will(returnValue(agentConfigurations));

				oneOf(agentPlatform_mock).startAgent(agentConfiguration_mock);
			}
		});

		testable.launch(configuration_mock);
	}
}
