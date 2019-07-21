package sample.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Session {

    private Scanner in;
    private PrintWriter out;
    private Server server;
    private String nickName;
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;

    public Session(Socket client, Server server) throws IOException {
        this.server = server;
        in = new Scanner(client.getInputStream());
        out = new PrintWriter(client.getOutputStream(),true);
    }

    public void start(){
        while(in.hasNextLine()){
            server.sendToAllClients(nickName,in.nextLine());
        }
        server.sendToAllClients(nickName,  " has disconnected");
    }

    public void send(String clientName, String msg) {
        time = new Date(); // текущая дата
        dt1 = new SimpleDateFormat("HH:mm:ss"); // берем только время до секунд
        dtime = dt1.format(time); // время

        out.println("(" + dtime + ") " + clientName + " >> " + msg);
    }

    public String getUserName() {
        if(in.hasNext()) {
            return nickName = in.nextLine();
        } else {
            return "error";
        }
    }
}
