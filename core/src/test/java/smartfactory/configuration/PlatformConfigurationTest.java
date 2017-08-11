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
import smartfactory.container.ContainerType;

public class PlatformConfigurationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	PlatformConfiguration platformConfiguration;

	@Before
	public void setUp() {
		platformConfiguration = new PlatformConfiguration();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void appendContainerName() {
		final String containerName = "test container";
		List<String> parameters = new ArrayList<String>();

		platformConfiguration.containerName = containerName;

		platformConfiguration.appendContainerName(parameters);
		Assert.assertEquals(2, parameters.size());
		Assert.assertEquals("-container-name", parameters.get(0));
		Assert.assertEquals(containerName, parameters.get(1));
	}

	@Test
	public void appendContainerName_null() {
		List<String> parameters = new ArrayList<String>();

		platformConfiguration.containerName = null;

		platformConfiguration.appendContainerName(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void appendGui() {
		List<String> parameters = new ArrayList<String>();

		platformConfiguration.gui = true;

		platformConfiguration.appendGui(parameters);
		Assert.assertEquals(1, parameters.size());
		Assert.assertEquals("-gui", parameters.get(0));
	}

	@Test
	public void appendGui_false() {
		List<String> parameters = new ArrayList<String>();

		platformConfiguration.gui = false;

		platformConfiguration.appendGui(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void appendHost() {
		final String host = "192.168.0.1";
		List<String> parameters = new ArrayList<String>();

		platformConfiguration.host = host;

		platformConfiguration.appendHost(parameters);
		Assert.assertEquals(2, parameters.size());
		Assert.assertEquals("-host", parameters.get(0));
		Assert.assertEquals(host, parameters.get(1));
	}

	@Test
	public void appendHost_null() {
		List<String> parameters = new ArrayList<String>();

		platformConfiguration.host = null;

		platformConfiguration.appendHost(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void appendLocalHost() {
		final String localHost = "192.168.0.2";
		List<String> parameters = new ArrayList<String>();

		platformConfiguration.localhost = localHost;

		platformConfiguration.appendLocalHost(parameters);
		Assert.assertEquals(2, parameters.size());
		Assert.assertEquals("-local-host", parameters.get(0));
		Assert.assertEquals(localHost, parameters.get(1));
	}

	@Test
	public void appendLocalHost_null() {
		List<String> parameters = new ArrayList<String>();

		platformConfiguration.localhost = null;

		platformConfiguration.appendLocalHost(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void appendContainerType_Container() {
		List<String> parameters = new ArrayList<String>();

		platformConfiguration.containerType = ContainerType.Container;

		platformConfiguration.appendContainerType(parameters);
		Assert.assertEquals(1, parameters.size());
		Assert.assertEquals("-container", parameters.get(0));
	}

	@Test
	public void appendContainerType_Main() {
		List<String> parameters = new ArrayList<String>();

		platformConfiguration.containerType = ContainerType.MainContainer;

		platformConfiguration.appendContainerType(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void appendContainerType_Default() {
		List<String> parameters = new ArrayList<String>();

		platformConfiguration.appendContainerType(parameters);
		Assert.assertEquals(0, parameters.size());
	}

	@Test
	public void getStartupParameters() {
		final String containerName = "test container";
		final String host = "192.168.88.1";
		final String localhost = "192.168.88.2";

		platformConfiguration.containerName = containerName;
		platformConfiguration.containerType = ContainerType.Container;
		platformConfiguration.gui = true;
		platformConfiguration.host = host;
		platformConfiguration.localhost = localhost;

		List<String> parameters = platformConfiguration.getStartupParameters();
		Assert.assertEquals(8, parameters.size());
		Assert.assertEquals("-container-name", parameters.get(0));
		Assert.assertEquals(containerName, parameters.get(1));
		Assert.assertEquals("-container", parameters.get(2));
		Assert.assertEquals("-gui", parameters.get(3));
		Assert.assertEquals("-host", parameters.get(4));
		Assert.assertEquals(host, parameters.get(5));
		Assert.assertEquals("-local-host", parameters.get(6));
		Assert.assertEquals(localhost, parameters.get(7));
	}

	@Test
	public void loadContainerName() {
		final String text = "text";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("container-name");
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		platformConfiguration.loadContainerName(root_mock);
		Assert.assertEquals(text, platformConfiguration.containerName);
	}

	@Test
	public void loadContainerName_default() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("container-name");
				will(returnValue(null));
			}
		});

		platformConfiguration.loadContainerName(root_mock);
		Assert.assertEquals(null, platformConfiguration.containerName);
	}

	@Test
	public void loadContainerType_mainContainer() {
		final String text = "MainContainer";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("container-type");
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		platformConfiguration.loadContainerType(root_mock);
		Assert.assertEquals(ContainerType.MainContainer, platformConfiguration.containerType);
	}

	@Test
	public void loadContainerType_container() {
		final String text = "Container";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("container-type");
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		platformConfiguration.loadContainerType(root_mock);
		Assert.assertEquals(ContainerType.Container, platformConfiguration.containerType);
	}

	@Test
	public void loadContainerType_default() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("container-type");
				will(returnValue(null));
			}
		});

		platformConfiguration.loadContainerType(root_mock);
		Assert.assertEquals(ContainerType.MainContainer, platformConfiguration.containerType);
	}

	@Test
	public void loadGui() {
		final String text = "true";
		Element root_mock = context.mock(Element.class, "root");
		Element element_mock = context.mock(Element.class, "element");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("gui");
				will(returnValue(element_mock));

				oneOf(element_mock).getTextTrim();
				will(returnValue(text));
			}
		});

		platformConfiguration.loadGui(root_mock);
		Assert.assertEquals(true, platformConfiguration.gui);
	}

	@Test
	public void loadGui_default() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("gui");
				will(returnValue(null));
			}
		});

		platformConfiguration.loadGui(root_mock);
		Assert.assertEquals(false, platformConfiguration.gui);
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

		platformConfiguration.loadHost(root_mock);
		Assert.assertEquals(text, platformConfiguration.host);
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

		platformConfiguration.loadHost(root_mock);
		Assert.assertEquals(null, platformConfiguration.host);
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

		platformConfiguration.loadLocalHost(root_mock);
		Assert.assertEquals(text, platformConfiguration.localhost);
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

		platformConfiguration.loadLocalHost(root_mock);
		Assert.assertEquals(null, platformConfiguration.localhost);
	}

	@Test
	public void load() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild("container-name");
				will(returnValue(null));

				oneOf(root_mock).getChild("container-type");
				will(returnValue(null));

				oneOf(root_mock).getChild("gui");
				will(returnValue(null));

				oneOf(root_mock).getChild("host");
				will(returnValue(null));

				oneOf(root_mock).getChild("local-host");
				will(returnValue(null));
			}
		});

		platformConfiguration.load(root_mock);
	}
}
