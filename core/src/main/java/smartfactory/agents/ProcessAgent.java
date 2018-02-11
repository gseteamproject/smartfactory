package smartfactory.agents;

import smartfactory.behaviours.process.ProcessBehaviour;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.models.Process;
import smartfactory.models.ProcessOperation;
import smartfactory.platform.JADEPlatform;

public class ProcessAgent extends BaseAgent {

	private static final long serialVersionUID = 4558559070184477446L;

	protected ProcessDataStore dataStore;

	@Override
	protected void setupData() {
		dataStore = new ProcessDataStore();
		dataStore.setProcess(createProcess());
		dataStore.setProcessOperation(new ProcessOperation());
		dataStore.setAgentPlatform(new JADEPlatform(this));
	}

	public Process createProcess() {
		return new Process();
	}

	@Override
	protected void setupSpecialBehaviours() {
		addBehaviour(new ProcessBehaviour(dataStore));
	}
}
