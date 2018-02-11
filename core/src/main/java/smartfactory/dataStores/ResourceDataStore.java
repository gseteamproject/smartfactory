package smartfactory.dataStores;

import smartfactory.models.Resource;

public class ResourceDataStore extends BaseDataStore {

	public void setResource(Resource machine) {
		put("resource", machine);
	}

	public Resource getResource() {
		// TODO : alert if there is no machine
		return (Resource) get("resource");
	}

	private static final long serialVersionUID = -2489538494906521330L;
}
