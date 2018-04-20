package basicAgents;

import java.util.ArrayList;
import java.util.List;

import basicClasses.MaterialStorage;
import basicClasses.Order;
import interactors.OrderDataStore;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import procurementBehaviours.ProcurementResponder;

public class Procurement extends Agent {
    /**
     * 
     */
    private static final long serialVersionUID = 2923962894395399488L;
    public static boolean isInMaterialStorage;
    public static boolean isGiven;
    protected OrderDataStore dataStore;

    // queue for procurement orders
    public static List<Order> procurementQueue = new ArrayList<Order>();

    // creating storage for raw materials
    public static MaterialStorage materialStorage = new MaterialStorage();

    @Override
    protected void setup() {
        MessageTemplate reqTemp = AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST);

        dataStore = new OrderDataStore();

        // adding behaviours
        addBehaviour(new ProcurementResponder(this, reqTemp, dataStore));
    }
}
