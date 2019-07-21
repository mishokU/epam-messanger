package sample.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private int port;
    private List<Session> clients;
    private ServerSocket server;

    public Server(int port){
        this.port = port;
        clients = new ArrayList<>();
    }

    public void start(){
        try {

            server = new ServerSocket(port);
            System.out.println("Server started!");

            while(true){

                Socket newClient = server.accept();
                System.out.println("New client !");
                Session newClientSession = new Session(newClient,this);
                sendToAllClients("Server: " + newClientSession.getUserName(), " connected");
                clients.add(newClientSession);
                new Thread(newClientSession::start).start();
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void updateClientsList() {
        clients.forEach(c -> {
            if(c.online()) {
                c.send("clientList",c.getUserName(),"");
            }
        });
    }

    public void sendToAllClients(String clientName, String msg){
        clients.forEach(c -> c.send("message",clientName, msg));
    }
}
