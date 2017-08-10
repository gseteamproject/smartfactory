package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.container.ContainerType;

public class PlatformConfigurationTest {

	PlatformConfiguration platformConfiguration;

	@Before
	public void setUp() {
		platformConfiguration = new PlatformConfiguration();
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
}
