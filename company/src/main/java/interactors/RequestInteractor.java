package interactors;

public class RequestInteractor {
    
    protected OrderDataStore dataStore;

    public RequestInteractor(OrderDataStore dataStore) {
        this.dataStore = dataStore;
    }
}
