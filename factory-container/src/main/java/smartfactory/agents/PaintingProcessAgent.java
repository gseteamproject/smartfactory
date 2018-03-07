package smartfactory.agents;

import smartfactory.models.PaintingProcess;
import smartfactory.models.Process;

public class PaintingProcessAgent extends ProcessAgent {

	private static final long serialVersionUID = 3440817403826196901L;

	@Override
	public Process createProcess() {
		return new PaintingProcess();
	}
}
