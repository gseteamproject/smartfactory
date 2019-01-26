package communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;
import jade.core.BaseService;
import jade.core.Profile;
import jade.core.ServiceException;
import jade.core.ServiceHelper;

public class ServerService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String NAME = "ServerService";

	public static final String DELAY_TIME = "server-delayTime";

	private Server server;

	public ServerService() {
		server = new Server();
		helper = new ServerServiceHelperImpl(server);
	}

	public ServerService(Server server, ServerServiceHelper helper) {
		this.server = server;
		this.helper = helper;
	}

	@Override
	public String getName() {
		return NAME;
	}

	private ServerServiceHelper helper;

	@Override
	public ServiceHelper getHelper(Agent a) {
		return helper;
	}

	@Override
	public void boot(Profile p) throws ServiceException {
		super.boot(p);

		long delayTime = Long.parseLong(p.getParameter(DELAY_TIME, "0"));
		server.setDelayTime(delayTime);
		logger.info("{}: {}", DELAY_TIME, delayTime);

		server.start();
	}

	@Override
	public void shutdown() {
		super.shutdown();

		server.stop();
	}
}
