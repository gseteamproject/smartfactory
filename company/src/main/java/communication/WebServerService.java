package communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;
import jade.core.BaseService;
import jade.core.Profile;
import jade.core.ServiceException;
import jade.core.ServiceHelper;

public class WebServerService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String NAME = "WebServerService";

	public static final String DELAY_TIME = "webServer-delayTime";

	@Override
	public String getName() {
		return NAME;
	}

	private WebServerHelper helper = new WebServerHelperImpl();

	@Override
	public ServiceHelper getHelper(Agent a) {
		return helper;
	}

	private long delayTime;

	@Override
	public void boot(Profile p) throws ServiceException {
		super.boot(p);
		delayTime = Long.parseLong(p.getParameter(DELAY_TIME, "0"));
		logger.info("delayTime: {}", delayTime);
	}
}
