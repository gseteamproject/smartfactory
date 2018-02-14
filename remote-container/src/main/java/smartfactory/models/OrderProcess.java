package smartfactory.models;

public class OrderProcess extends Process {

	public OrderProcess() {
		super();
		// TODO : move text constant to separate class
		operations.add(new ProcessOperation("block-production"));
	}
}
