package communication;

public class Communication {
    public static Server server;

    public Communication() {
        server = new Server();

        Thread thread = new Thread(server);
        thread.start();
    }

}
