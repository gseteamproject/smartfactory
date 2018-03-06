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
				oneOf(root_mock).getChild(Tag.AGENT_NAME);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadName(root_mock);
		Assert.assertEquals(text, testable.name);
	}

	@Test
	public void loadClassName() {
		final String text = "package:class";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(Tag.AGENT_CLASS);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadClassName(root_mock);
		Assert.assertEquals(text, testable.className);
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
				oneOf(root_mock).getChild("parameters");
				will(returnValue(element_mock));

				oneOf(element_mock).getChildren("parameter");
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
	public void loadParameters_no_parameters() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("parameters");
				will(returnValue(null));
			}
		});

		testable.loadParameters(root_mock);
		Assert.assertEquals(0, testable.parameters.size());
	}

	@Test
	public void load() {
		final String text1 = "text1";
		final String text2 = "text2";
		Element root_mock = context.mock(Element.class, "root");
		Element element1_mock = context.mock(Element.class, "element1");
		Element element2_mock = context.mock(Element.class, "element2");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(Tag.AGENT_NAME);
				will(returnValue(element1_mock));

				oneOf(element1_mock).getTextTrim();
				will(returnValue(text1));

				oneOf(root_mock).getChild(Tag.AGENT_CLASS);
				will(returnValue(element2_mock));

				oneOf(element2_mock).getTextTrim();
				will(returnValue(text2));

				oneOf(root_mock).getChild(Tag.AGENT_PARAMETERS);
				will(returnValue(null));
			}
		});

		testable.load(root_mock);
	}

	@Test
	public void getAgentName() {
		final String agentName = "instance";

		testable.name = agentName;

		Assert.assertEquals(agentName, testable.getAgentName());
	}

	@Test
	public void getAgentClass() {
		final String agentClass = "class";

		testable.className = agentClass;

		Assert.assertEquals(agentClass, testable.getAgentClass());
	}

	@Test
	public void getAgentParameters() {
		final String parameter = "parameter";

		testable.parameters.add(parameter);

		Object[] parameters = testable.getAgentParameters();
		Assert.assertEquals(1, parameters.length);
		Assert.assertEquals(parameter, parameters[0]);
	}
}
