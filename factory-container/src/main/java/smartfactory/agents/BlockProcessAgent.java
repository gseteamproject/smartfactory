package smartfactory.agents;

import smartfactory.models.BlockProcess;
import smartfactory.models.Process;

public class BlockProcessAgent extends ProcessAgent {

	static public String getUniqueName() {
		return "block-" + Long.toString(System.currentTimeMillis());
	}

	@Override
	public Process createProcess() {
		return new BlockProcess();
	}

	private static final long serialVersionUID = 9181639673522822855L;
}
