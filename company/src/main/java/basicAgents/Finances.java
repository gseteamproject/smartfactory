package basicAgents;

import basicClasses.ProductStorage;
import financesBehaviours.FinancesResponder;
import interactors.OrderDataStore;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

public class Finances extends Agent {
    /**
     * 
     */
    private static final long serialVersionUID = 4773016963292343207L;
    public ACLMessage starterMessage;
    public boolean isInWarehouse;
    protected OrderDataStore dataStore;

    // creating storage for products
    public static ProductStorage warehouse = new ProductStorage();

    @Override
    protected void setup() {
        MessageTemplate reqTemp = AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST);

        dataStore = new OrderDataStore();

        // adding behaviours
        addBehaviour(new FinancesResponder(this, reqTemp, dataStore));
    }

}
