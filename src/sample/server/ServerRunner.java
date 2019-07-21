package sample.server;

public class ServerRunner {

    private static int port = 8081;

    public static void main(String[] args) {
        Server server = new Server(port);
        server.start();
    }
}
