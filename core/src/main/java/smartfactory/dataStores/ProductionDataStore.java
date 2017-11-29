package smartfactory.dataStores;

import smartfactory.models.Production;

public class ProductionDataStore extends BaseDataStore {

	private static final long serialVersionUID = 9041457631412904069L;

	public void setProduction(Production production) {
		put("production", production);
	}

	public Production getProduction() {
		return (Production) get("production");
	}
}
