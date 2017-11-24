package smartfactory.models;

import org.apache.commons.lang3.StringUtils;

public class ProductionProvisioning {

	public static final int AgentsFound = 0;
	public static final int AgentsNotFound = 1;
	public static final int AgentSelected = 0;
	public static final int AgentNotSelected = 1;
	public static final int ProductionPerformedSuccessfully = 0;
	public static final int ProductionPerformedUnSuccessfully = 1;

	public String productionName;

	public static final int ProductionDetermined = 0;
	public static final int ProductionNotDetermined = 1;

	public int isProductionDetermined() {
		if (StringUtils.isEmpty(productionName)) {
			return ProductionNotDetermined;
		}
		return ProductionDetermined;
	}

}
