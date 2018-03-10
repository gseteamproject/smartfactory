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

public class AgentConfigurationsTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentConfigurations testable;

	@Before
	public void setUp() {
		testable = new AgentConfigurations();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void load() {
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");
		List<Element> elements = new ArrayList<Element>();
		elements.add(element_mock);

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChildren(ConfigurationTag.AGENT);
				will(returnValue(elements));

				oneOf(element_mock).getChild(ConfigurationTag.AGENT_NAME);

				oneOf(element_mock).getChild(ConfigurationTag.AGENT_CLASS);

				oneOf(element_mock).getChild(ConfigurationTag.AGENT_RESOURCE);
				will(returnValue(null));

				oneOf(element_mock).getChild(ConfigurationTag.AGENT_PARAMETERS);
				will(returnValue(null));
			}
		});

		testable.load(root_mock);
		Assert.assertEquals(1, testable.agentConfigurations.size());
	}

	@Test
	public void asList() {
		Assert.assertEquals(testable.agentConfigurations, testable.asList());
	}
}
