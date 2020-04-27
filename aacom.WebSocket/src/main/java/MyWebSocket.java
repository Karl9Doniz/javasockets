import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Timer;

public class MyWebSocket extends WebSocketServer {

    private static final int PORT = 1111;
    private Set<WebSocket> cons; //set of connections
    private Map<String, String> users = new HashMap<>();
    private static String ipinfo;

    MyWebSocket() { //constructor

        super(new InetSocketAddress(PORT));
        cons = new HashSet<WebSocket>();

    }

    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        cons.add(webSocket); //add to set
        System.out.println("New connection from: " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());
        webSocket.send("Hello!");
    }


    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        cons.remove(webSocket); //delete from set
        System.out.println("Closed connection to: " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());
        File file = new File("C:\\Users\\alexa\\IdeaProjects\\aacom.WebSocket\\src\\main\\java\\userip.txt");

    }

    public void onMessage(WebSocket webSocket, String message) {
        String filepath = "C:\\Users\\alexa\\IdeaProjects\\aacom.WebSocket\\src\\main\\java\\userinfo.txt";
        String ipdir = "C:\\Users\\alexa\\IdeaProjects\\aacom.WebSocket\\src\\main\\java\\userip.txt";

        JSONObject obj = JSON.parseObject(message);
        if (obj.getString("type").equals("registration")) {
            String user = obj.getString("name");
            String pass = obj.getString("pass");
            String info = user + "," + pass;
            String userip = user + "," + webSocket.getRemoteSocketAddress().getAddress().getHostAddress();
            ipinfo = userip;
            // registration
            try (FileWriter fw1 = new FileWriter(filepath, true);
                 BufferedWriter bw1 = new BufferedWriter(fw1);
                 PrintWriter out1 = new PrintWriter(bw1))

            {
                out1.print(info + "\n");
                webSocket.send("Welcome, " + user + "!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //adding new ip
            try (FileWriter fw = new FileWriter(ipdir, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw))

            {
                out.print(userip + "\n");
                System.out.println("new ip: " + userip);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (obj.getString("type").equals("login")) {
            String user = obj.getString("name");
            String pass = obj.getString("pass");
            String info = user + "," + pass;
            String userip = user + "," + webSocket.getRemoteSocketAddress().getAddress().getHostAddress();
            File file = new File(filepath);
            //
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.equals(info)) {
                        webSocket.send("Welcome back!");
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //adding new ip
            try (FileWriter fw = new FileWriter(ipdir, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw))

            {
                out.print(userip + "\n");
                System.out.println("new ip: " + userip);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (obj.getString("type").equals("message")) {
            String user = obj.getString("name");
            String text = obj.getString("text");
            String info = user + ": " + text;

            webSocket.send(info);
        }

    }

    public void onError(WebSocket webSocket, Exception e) {

    }

    public void onStart() {

    }

}
