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

	AgentConfiguration agentConfiguration;

	@Before
	public void setUp() {
		agentConfiguration = new AgentConfiguration();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getStartupParameters_no_parameters() {
		final String name = "instanceName";
		final String className = "packageName.className";

		agentConfiguration.name = name;
		agentConfiguration.className = className;

		String startupParameters = agentConfiguration.getStartupParameters();
		Assert.assertEquals(name + ":" + className, startupParameters);
	}

	@Test
	public void getStartupParameters_with_parameters() {
		final String name = "instanceName";
		final String className = "packageName.className";
		final String parameter1 = "parameter1";
		final String parameter2 = "parameter2";

		agentConfiguration.name = name;
		agentConfiguration.className = className;
		agentConfiguration.parameters.add(parameter1);
		agentConfiguration.parameters.add(parameter2);

		String startupParameters = agentConfiguration.getStartupParameters();
		Assert.assertEquals(name + ":" + className + "(" + parameter1 + "," + parameter2 + ")", startupParameters);
	}

	@Test
	public void loadName() {
		final String text = "instanceName";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(AgentConfiguration.TAG_NAME);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		agentConfiguration.loadName(root_mock);
		Assert.assertEquals(text, agentConfiguration.name);
	}

	@Test
	public void loadClassName() {
		final String text = "package:class";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(AgentConfiguration.TAG_CLASS_NAME);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		agentConfiguration.loadClassName(root_mock);
		Assert.assertEquals(text, agentConfiguration.className);
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

		agentConfiguration.loadParameters(root_mock);
		Assert.assertEquals(2, agentConfiguration.parameters.size());
		Assert.assertEquals(text1, agentConfiguration.parameters.get(0));
		Assert.assertEquals(text2, agentConfiguration.parameters.get(1));
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

		agentConfiguration.loadParameters(root_mock);
		Assert.assertEquals(0, agentConfiguration.parameters.size());
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
				oneOf(root_mock).getChild(AgentConfiguration.TAG_NAME);
				will(returnValue(element1_mock));

				oneOf(element1_mock).getTextTrim();
				will(returnValue(text1));

				oneOf(root_mock).getChild(AgentConfiguration.TAG_CLASS_NAME);
				will(returnValue(element2_mock));

				oneOf(element2_mock).getTextTrim();
				will(returnValue(text2));

				oneOf(root_mock).getChild(AgentConfiguration.TAG_PARAMETERS);
				will(returnValue(null));
			}
		});

		agentConfiguration.load(root_mock);
	}
}
