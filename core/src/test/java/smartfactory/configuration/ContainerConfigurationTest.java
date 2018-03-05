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
import org.junit.Ignore;
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
	public void appendContainerName() {
		final String containerName = "test container";
		List<String> parameters = new ArrayList<String>();

		testable.containerName = containerName;

		testable.appendContainerName(parameters);
		Assert.assertEquals(2, parameters.size());
		Assert.assertEquals("-container-name", parameters.get(0));
		Assert.assertEquals(containerName, parameters.get(1));
	}

	@Test
	public void appendContainerName_null() {
		List<String> parameters = new ArrayList<String>();

		testable.containerName = null;

		testable.appendContainerName(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void appendRma() {
		List<String> parameters = new ArrayList<String>();

		testable.rma = true;

		testable.appendGui(parameters);
		Assert.assertEquals(1, parameters.size());
		Assert.assertEquals("-gui", parameters.get(0));
	}

	@Test
	public void appendRma_false() {
		List<String> parameters = new ArrayList<String>();

		testable.rma = false;

		testable.appendGui(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void appendContainerType_Container() {
		List<String> parameters = new ArrayList<String>();

		testable.containerType = ContainerType.Container;

		testable.appendContainerType(parameters);
		Assert.assertEquals(1, parameters.size());
		Assert.assertEquals("-container", parameters.get(0));
	}

	@Test
	public void appendContainerType_Main() {
		List<String> parameters = new ArrayList<String>();

		testable.containerType = ContainerType.MainContainer;

		testable.appendContainerType(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void appendContainerType_Default() {
		List<String> parameters = new ArrayList<String>();

		testable.appendContainerType(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void loadContainerName() {
		final String text = "text";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ContainerConfiguration.TAG_CONTAINER_NAME);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadContainerName(root_mock);
		Assert.assertEquals(text, testable.containerName);
	}

	@Test
	public void loadContainerName_default() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ContainerConfiguration.TAG_CONTAINER_NAME);
				will(returnValue(null));
			}
		});

		testable.loadContainerName(root_mock);
		Assert.assertEquals(null, testable.containerName);
	}

	@Test
	public void loadContainerType_mainContainer() {
		final String text = "MainContainer";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ContainerConfiguration.TAG_CONTAINER_TYPE);
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
				oneOf(root_mock).getChild(ContainerConfiguration.TAG_CONTAINER_TYPE);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadContainerType(root_mock);
		Assert.assertEquals(ContainerType.Container, testable.containerType);
	}

	@Test
	public void loadContainerType_default() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ContainerConfiguration.TAG_CONTAINER_TYPE);
				will(returnValue(null));
			}
		});

		testable.loadContainerType(root_mock);
		Assert.assertEquals(ContainerType.MainContainer, testable.containerType);
	}

	@Test
	public void loadRma() {
		final String text = "true";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ContainerConfiguration.TAG_RMA);
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadRma(root_mock);
		Assert.assertEquals(true, testable.rma);
	}

	@Test
	public void loadRma_default() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ContainerConfiguration.TAG_RMA);
				will(returnValue(null));
			}
		});

		testable.loadRma(root_mock);
		Assert.assertEquals(false, testable.rma);
	}

	@Test
	@Ignore
	public void getStartupParameters() {
		// TODO : fix
		final String containerName = "test container";
		final String host = "192.168.88.1";
		final String localhost = "192.168.88.2";

		testable.containerName = containerName;
		testable.containerType = ContainerType.Container;
		testable.rma = true;
		testable.host = host;
		testable.localhost = localhost;

		List<String> parameters = testable.getStartupParameters();
		Assert.assertEquals(8, parameters.size());
		Assert.assertEquals("-container-name", parameters.get(0));
		Assert.assertEquals(containerName, parameters.get(1));
		Assert.assertEquals("-container", parameters.get(2));
		Assert.assertEquals("-gui", parameters.get(3));
		Assert.assertEquals("-host", parameters.get(0));
		Assert.assertEquals(host, parameters.get(1));
		Assert.assertEquals("-local-host", parameters.get(2));
		Assert.assertEquals(localhost, parameters.get(3));
	}

	@Test
	public void appendHost() {
		final String host = "192.168.0.1";
		List<String> parameters = new ArrayList<String>();

		testable.host = host;

		testable.appendHost(parameters);
		Assert.assertEquals(2, parameters.size());
		Assert.assertEquals("-host", parameters.get(0));
		Assert.assertEquals(host, parameters.get(1));
	}

	@Test
	public void appendHost_null() {
		List<String> parameters = new ArrayList<String>();

		testable.host = null;

		testable.appendHost(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void appendLocalHost() {
		final String localHost = "192.168.0.2";
		List<String> parameters = new ArrayList<String>();

		testable.localhost = localHost;

		testable.appendLocalHost(parameters);
		Assert.assertEquals(2, parameters.size());
		Assert.assertEquals("-local-host", parameters.get(0));
		Assert.assertEquals(localHost, parameters.get(1));
	}

	@Test
	public void appendLocalHost_null() {
		List<String> parameters = new ArrayList<String>();

		testable.localhost = null;

		testable.appendLocalHost(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void loadHost() {
		final String text = "192.168.0.1";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("host");
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadHost(root_mock);
		Assert.assertEquals(text, testable.host);
	}

	@Test
	public void loadHost_default() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("host");
				will(returnValue(null));
			}
		});

		testable.loadHost(root_mock);
		Assert.assertEquals(null, testable.host);
	}

	@Test
	public void loadLocalHost() {
		final String text = "192.168.0.1";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("local-host");
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		testable.loadLocalHost(root_mock);
		Assert.assertEquals(text, testable.localhost);
	}

	@Test
	public void loadLocalHost_default() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("local-host");
				will(returnValue(null));
			}
		});

		testable.loadLocalHost(root_mock);
		Assert.assertEquals(null, testable.localhost);
	}

	@Test
	@Ignore
	public void load() {
		// TODO : fix
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("host");
				will(returnValue(null));

				oneOf(root_mock).getChild("local-host");
				will(returnValue(null));
			}
		});

		testable.load(root_mock);
	}
}
