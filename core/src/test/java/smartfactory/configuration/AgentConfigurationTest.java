package smartfactory.configuration;

import org.jdom2.Element;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AgentConfigurationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentConfiguration testable;

	@Before
	public void setUp() {
		testable = new AgentConfiguration();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void loadName() {
		final String text = "instanceName";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.AGENT_NAME);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadName(root_mock);
		Assert.assertEquals(text, testable.agentName);
	}

	@Test
	public void loadClassName() {
		final String text = "package:class";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.AGENT_CLASS);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadClassName(root_mock);
		Assert.assertEquals(text, testable.agentClass);
	}

	@Test
	public void loadResourceConfiguration() {
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		ResourceConfiguration configuration_mock = context.mock(ResourceConfiguration.class);
		testable.resourceConfiguration = configuration_mock;

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.RESOURCE);
				will(returnValue(element_mock));

				oneOf(configuration_mock).load(element_mock);
			}
		});

		testable.loadResourceConfiguration(root_mock);
	}

	@Test
	public void loadProcessConfiguration() {
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		ProcessConfiguration configuration_mock = context.mock(ProcessConfiguration.class);
		testable.processConfiguration = configuration_mock;

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.PROCESS);
				will(returnValue(element_mock));

				oneOf(configuration_mock).load(element_mock);
			}
		});

		testable.loadProcessConfiguration(root_mock);
	}

	@Test
	public void load() {
		final String agentName = "name";
		final String agentClass = "class";
		Element root_mock = context.mock(Element.class, "root");
		Element nameElement_mock = context.mock(Element.class, ConfigurationTag.AGENT_NAME);
		Element classElement_mock = context.mock(Element.class, ConfigurationTag.AGENT_CLASS);

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.AGENT_NAME);
				will(returnValue(nameElement_mock));

				oneOf(nameElement_mock).getTextTrim();
				will(returnValue(agentName));

				oneOf(root_mock).getChild(ConfigurationTag.AGENT_CLASS);
				will(returnValue(classElement_mock));

				oneOf(classElement_mock).getTextTrim();
				will(returnValue(agentClass));

				oneOf(root_mock).getChild(ConfigurationTag.RESOURCE);
				will(returnValue(null));

				oneOf(root_mock).getChild(ConfigurationTag.PROCESS);
				will(returnValue(null));
			}
		});

		testable.load(root_mock);

		Assert.assertEquals(agentName, testable.agentName);
		Assert.assertEquals(agentClass, testable.agentClass);
	}

	@Test
	public void getAgentName() {
		Assert.assertEquals(testable.agentName, testable.getAgentName());
	}

	@Test
	public void getAgentClass() {
		Assert.assertEquals(testable.agentClass, testable.getAgentClass());
	}

	@Test
	public void getAgentParameters() {
		Assert.assertArrayEquals(new Object[] { testable }, testable.getAgentParameters());
	}

	@Test
	public void getResourceConfiguration() {
		Assert.assertEquals(testable.resourceConfiguration, testable.getResourceConfiguration());
	}

	@Test
	public void getProcessConfiguration() {
		Assert.assertEquals(testable.processConfiguration, testable.getProcessConfiguration());
	}
}
