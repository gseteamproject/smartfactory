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

	Configuration testable;

	@Before
	public void startUp() {
		containerConfiguration_mock = context.mock(ContainerConfiguration.class);

		testable = new Configuration(containerConfiguration_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void load() {
		Element root_mock = context.mock(Element.class);
		Element container_mock = context.mock(Element.class, ConfigurationTag.CONTAINER);
		Element agents_mock = context.mock(Element.class, ConfigurationTag.AGENTS);
		Element agent_mock = context.mock(Element.class, ConfigurationTag.AGENT);
		List<Element> agents = new ArrayList<Element>();
		agents.add(agent_mock);

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.CONTAINER);
				will(returnValue(container_mock));

				oneOf(containerConfiguration_mock).load(container_mock);

				oneOf(root_mock).getChild(ConfigurationTag.AGENTS);
				will(returnValue(agents_mock));

				oneOf(agents_mock).getChildren(ConfigurationTag.AGENT);
				will(returnValue(agents));

				oneOf(agent_mock).getChild(ConfigurationTag.AGENT_NAME);

				oneOf(agent_mock).getChild(ConfigurationTag.AGENT_CLASS);

				oneOf(agent_mock).getChild(ConfigurationTag.RESOURCE);
				will(returnValue(null));

				oneOf(agent_mock).getChild(ConfigurationTag.PROCESS);
				will(returnValue(null));
			}
		});

		testable.load(root_mock);
	}

	@Test
	public void getContainerConfiguration() {
		Assert.assertEquals(containerConfiguration_mock, testable.getContainerConfiguration());
	}

	@Test
	public void getAgentsConfigurations() {
		Assert.assertEquals(testable.agentConfigurations, testable.getAgentConfigurations());
	}
}
