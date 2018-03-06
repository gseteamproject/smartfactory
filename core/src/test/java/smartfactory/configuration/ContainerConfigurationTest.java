package smartfactory.configuration;

import org.jdom2.Element;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.container.ContainerType;

public class ContainerConfigurationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ContainerConfiguration testable;

	@Before
	public void setUp() {
		testable = new ContainerConfiguration();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void loadContainerName() {
		final String text = "ContainerName";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.CONTAINER_NAME);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadContainerName(root_mock);
		Assert.assertEquals(text, testable.containerName);
	}

	@Test
	public void loadContainerType_mainContainer() {
		final String text = "MainContainer";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.CONTAINER_TYPE);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadContainerType(root_mock);
		Assert.assertEquals(ContainerType.MainContainer, testable.containerType);
	}

	@Test
	public void loadContainerType_container() {
		final String text = "Container";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.CONTAINER_TYPE);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadContainerType(root_mock);
		Assert.assertEquals(ContainerType.Container, testable.containerType);
	}

	@Test
	public void loadRma() {
		final String text = "true";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.RMA);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadRma(root_mock);
		Assert.assertEquals(true, testable.rma);
	}

	@Test
	public void loadHost() {
		final String text = "192.168.0.1";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.HOST);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadHost(root_mock);
		Assert.assertEquals(text, testable.host);
	}

	@Test
	public void loadLocalHost() {
		final String text = "192.168.0.1";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.LOCAL_HOST);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadLocalHost(root_mock);
		Assert.assertEquals(text, testable.localhost);
	}

	@Test
	public void load() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.CONTAINER_NAME);
				will(returnValue(null));

				oneOf(root_mock).getChild(ConfigurationTag.CONTAINER_TYPE);
				will(returnValue(null));

				oneOf(root_mock).getChild(ConfigurationTag.RMA);
				will(returnValue(null));

				oneOf(root_mock).getChild(ConfigurationTag.HOST);
				will(returnValue(null));

				oneOf(root_mock).getChild(ConfigurationTag.LOCAL_HOST);
				will(returnValue(null));
			}
		});

		testable.load(root_mock);

		Assert.assertEquals(null, testable.containerName);
		Assert.assertEquals(ContainerType.MainContainer, testable.containerType);
		Assert.assertEquals(false, testable.rma);
		Assert.assertEquals(null, testable.host);
		Assert.assertEquals(null, testable.localhost);
	}

	@Test
	public void getContainerName() {
		final String containerName = "name";

		testable.containerName = containerName;

		Assert.assertEquals(containerName, testable.getContainerName());
	}

	@Test
	public void getMainHost() {
		final String host = "192.168.100.1";

		testable.host = host;

		Assert.assertEquals(host, testable.getMainHost());
	}

	@Test
	public void getLocalHost() {
		final String localHost = "192.168.100.2";

		testable.localhost = localHost;

		Assert.assertEquals(localHost, testable.getLocalHost());
	}

	@Test
	public void getGUI() {
		testable.rma = true;

		Assert.assertTrue(testable.getGUI());
	}

	@Test
	public void getContainerType() {
		testable.containerType = ContainerType.Container;

		Assert.assertEquals(ContainerType.Container, testable.getContainerType());
	}
}
