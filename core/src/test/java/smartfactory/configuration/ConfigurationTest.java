package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ContainerConfiguration containerConfiguration_mock;
	AgentConfigurations agentConfigurations_mock;

	Configuration testable;

	@Before
	public void startUp() {
		containerConfiguration_mock = context.mock(ContainerConfiguration.class);
		agentConfigurations_mock = context.mock(AgentConfigurations.class);

		testable = new Configuration(containerConfiguration_mock, agentConfigurations_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void load() {
		Element rootElement_mock = context.mock(Element.class);
		Element containerElement_mock = context.mock(Element.class, ConfigurationTag.CONTAINER);
		Element agentsElement_mock = context.mock(Element.class, ConfigurationTag.AGENTS);

		context.checking(new Expectations() {
			{
				oneOf(rootElement_mock).getChild(ConfigurationTag.CONTAINER);
				will(returnValue(containerElement_mock));

				oneOf(containerConfiguration_mock).load(containerElement_mock);

				oneOf(rootElement_mock).getChild(ConfigurationTag.AGENTS);
				will(returnValue(agentsElement_mock));

				oneOf(agentConfigurations_mock).load(agentsElement_mock);
			}
		});

		testable.load(rootElement_mock);
	}

	@Test
	public void getContainerConfiguration() {
		Assert.assertEquals(containerConfiguration_mock, testable.getContainerConfiguration());
	}

	@Test
	public void getAgentsConfigurations() {
		List<AgentConfiguration> agentConfigurations = new ArrayList<AgentConfiguration>();

		context.checking(new Expectations() {
			{
				oneOf(agentConfigurations_mock).asList();
				will(returnValue(agentConfigurations));
			}
		});

		Assert.assertEquals(agentConfigurations, testable.getAgentConfigurations());
	}
}
