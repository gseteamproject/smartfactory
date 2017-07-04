package smartfactory.core;

public class MyAgent {

	private String name;

	public MyAgent(String name) {
		this.name = name;
	}

	public void introduceItself() {
		System.out.println("Hello I am " + name);
	}
}
