package smartfactory.dataStores;

import jade.core.behaviours.DataStore;
import smartfactory.models.Machine;

public class MachineDataStore extends DataStore {

	public void setMachine(Machine machine) {
		put("machine", machine);
	}

	public Machine getMachine() {
		return (Machine) get("machine");
	}

	private static final long serialVersionUID = -2489538494906521330L;
}
