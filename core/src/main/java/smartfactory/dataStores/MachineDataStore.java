package smartfactory.dataStores;

import smartfactory.models.Machine;

public class MachineDataStore extends SmartFactoryDataStore {

	public void setMachine(Machine machine) {
		put("machine", machine);
	}

	public Machine getMachine() {
		// TODO : alert if there is no machine
		return (Machine) get("machine");
	}

	private static final long serialVersionUID = -2489538494906521330L;
}
