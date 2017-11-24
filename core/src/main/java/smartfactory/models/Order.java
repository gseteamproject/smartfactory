package smartfactory.models;

public class Order {

	private String requiredProductionName;

	public String getRequiredProductionName() {
		return requiredProductionName;
	}

	public ProductionProvisioning createProductionProvisioning() {
		ProductionProvisioning productionProvisioning = new ProductionProvisioning();
		productionProvisioning.productionName = getRequiredProductionName();
		return productionProvisioning;
	}
	
	public static final int IsInTheLastState = 0;
	public static final int InNotInTheLastState = 1;

	public int isInTheLastState() {
		return IsInTheLastState;
	}

	public void moveToNextState() {
		// TODO Auto-generated method stub
	}
}
