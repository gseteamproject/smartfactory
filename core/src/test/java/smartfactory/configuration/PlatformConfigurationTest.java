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

public class PlatformConfigurationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	PlatformConfiguration testable;

	@Before
	public void setUp() {
		testable = new PlatformConfiguration();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
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
	public void getStartupParameters() {
		final String host = "192.168.88.1";
		final String localhost = "192.168.88.2";

		testable.host = host;
		testable.localhost = localhost;

		List<String> parameters = testable.getStartupParameters();
		Assert.assertEquals(4, parameters.size());
		Assert.assertEquals("-host", parameters.get(0));
		Assert.assertEquals(host, parameters.get(1));
		Assert.assertEquals("-local-host", parameters.get(2));
		Assert.assertEquals(localhost, parameters.get(3));
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
	public void load() {
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
