package communication;

import java.util.HashMap;
import java.util.Map;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;

import jade.lang.acl.ACLMessage;

/*
    SocketIO implementation to communicate with the client website
 */
public class Server implements Runnable {
    private final String server = "localhost";
    private final int port = 9092;
    public static long delaytime;

    private SocketIOServer conServer;
    private SocketIOClient conClient;
    private int connectionCounter;

    private Map<String, JsonWrapper> arrows;

    @Override
    /*
     * just an example of the communication, not finished yet.
     */
    public void run() {
        System.out.println("[Server] -> Started on port " + port);
        connectionCounter = 0;

        arrows = new HashMap<>();

        conServer = new SocketIOServer(getConfig());

        /*
         * get all incoming connections to the server allow only one connection
         */
        conServer.addConnectListener(socketIOClient -> {
            // allow only one client connection
            if (connectionCounter >= 1) {
                socketIOClient.disconnect();
                return;
            }

            // count the connections
            connectionCounter++;

            // set the client
            conClient = socketIOClient;
        });
        /*
         * handle all disconnects
         */
        conServer.addDisconnectListener(socketIOClient -> {
            // count connections
            if (connectionCounter >= 1)
                connectionCounter--;
        });

        /*
         * receiving messages from the client
         */
        conServer.addEventListener("msgevent", MessageObject.class, (socketIOClient, messageObject, ackRequest) -> {
            if (messageObject.getMessage().equals("stop_server")) {
                System.out.println("[Server] -> Client stopped the server!");
                stopServer();
            }
        });

        conServer.start();
    }

    public void sendDataToClient(String event, MessageObject object) {
        if (conClient != null)
            conClient.sendEvent(event, object);
    }

    public void sendMessageToClient(MessageObject msgObj) {
        MessageWrapper wrapper = new MessageWrapper(msgObj);

        try {
            Thread.sleep(delaytime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (conClient != null) {
            if (msgObj.getReceiver() != null) {
                String from = msgObj.getSender().replace("Agent", "");
                String to = msgObj.getReceiver().replace("Agent", "");

                String json = "[";
                String identifier = from + ";" + to;
                JsonWrapper jsonWrapper = new JsonWrapper();
                jsonWrapper.setFrom(from);
                jsonWrapper.setTo(to);
                String text = msgObj.getPerformative() + ": " + msgObj.getOrderText();
                jsonWrapper.setText(text);

                for (Map.Entry<String, JsonWrapper> entry : arrows.entrySet()) {
                    entry.getValue().setText("");
                }

                arrows.put(identifier, jsonWrapper);

                for (Map.Entry<String, JsonWrapper> entry : arrows.entrySet()) {
                    json += "{\"from\": \"" + entry.getValue().getFrom() + "\", \"to\": \"" + entry.getValue().getTo()
                            + "\", \"color\": \"red\", \"text\": \"" + entry.getValue().getText() + "\"},";
                }

                json = json.substring(0, json.length() - 1);
                json += "]";

                // System.out.println(json);
                wrapper.setMessage(json);
                conClient.sendEvent("jsonevent", wrapper);

            } else {
                conClient.sendEvent("alcevent", wrapper);
            }
        }

    }

    public void sendMessageToClient(ACLMessage acl, String ordertext) {
        MessageWrapper wrapper = new MessageWrapper(new MessageObject(acl, ordertext));

        if (conClient != null)
            conClient.sendEvent("alcevent", wrapper);
    }

    public void sendMessageToClient(String sender, String msg) {
        if (conClient != null)
            conClient.sendEvent("msgevent", new MessageObject(sender, msg));
    }

    /*
     * setup configuration
     */
    private Configuration getConfig() {
        Configuration config = new Configuration();
        config.setHostname(server);
        config.setPort(port);
        return config;
    }

    private void stopServer() {
        if (conClient != null)
            conClient.disconnect();

        if (conServer != null)
            conServer.stop();
    }
}
