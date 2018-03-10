package smartfactory.configuration;

import org.jdom2.Element;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.models.ResourceType;

public class ResourceConfigurationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ResourceConfiguration testable;

	@Before
	public void setUp() {
		testable = new ResourceConfiguration();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void loadResourceType() {
		final String text = "virtual";

		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.RESOURCE_TYPE);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadResourceType(root_mock);
		Assert.assertEquals(ResourceType.virtual, testable.resourceType);
	}

	@Test
	public void load() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.RESOURCE_TYPE);
				will(returnValue(null));
			}
		});

		testable.load(root_mock);

		Assert.assertEquals(ResourceType.none, testable.resourceType);
	}

	@Test
	public void getResourceType() {
		final ResourceType resourceType = ResourceType.none;

		testable.resourceType = resourceType;

		Assert.assertEquals(resourceType, testable.getResourceType());
	}
}
