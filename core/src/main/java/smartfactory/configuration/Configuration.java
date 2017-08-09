package smartfactory.configuration;

public class Configuration {

	public PlatformConfiguration platform;

	public String[] getCommandLineParameters() {
		return new String[] { "-gui" };
	}

	public void load() {
	}
}
