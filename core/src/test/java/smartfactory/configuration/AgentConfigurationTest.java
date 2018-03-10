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
	public void loadParameters() {
		final String text1 = "text1";
		final String text2 = "text2";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");
		List<Element> elements = new ArrayList<Element>();
		Element element1_mock = context.mock(Element.class, "parameter1");
		Element element2_mock = context.mock(Element.class, "parameter2");
		elements.add(element1_mock);
		elements.add(element2_mock);

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.AGENT_PARAMETERS);
				will(returnValue(element_mock));

				oneOf(element_mock).getChildren(ConfigurationTag.AGENT_PARAMETERS_PARAMETER);
				will(returnValue(elements));

				oneOf(element1_mock).getTextTrim();
				will(returnValue(text1));

				oneOf(element2_mock).getTextTrim();
				will(returnValue(text2));
			}
		});

		testable.loadParameters(root_mock);
		Assert.assertEquals(2, testable.parameters.size());
		Assert.assertEquals(text1, testable.parameters.get(0));
		Assert.assertEquals(text2, testable.parameters.get(1));
	}

	@Test
	public void loadResourceConfiguration() {
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		ResourceConfiguration resourceConfiguration_mock = context.mock(ResourceConfiguration.class);
		testable.resourceConfiguration = resourceConfiguration_mock;

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.AGENT_RESOURCE);
				will(returnValue(element_mock));

				oneOf(resourceConfiguration_mock).load(element_mock);
			}
		});

		testable.loadResourceConfiguration(root_mock);
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

				oneOf(root_mock).getChild(ConfigurationTag.AGENT_RESOURCE);
				will(returnValue(null));

				oneOf(root_mock).getChild(ConfigurationTag.AGENT_PARAMETERS);
				will(returnValue(null));
			}
		});

		testable.load(root_mock);

		Assert.assertEquals(agentName, testable.agentName);
		Assert.assertEquals(agentClass, testable.agentClass);
		Assert.assertEquals(0, testable.parameters.size());
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
}
