import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
//rrr
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
    private Map<String, Boolean> authUsers = new HashMap<>();

    MyWebSocket() { //constructor

        super(new InetSocketAddress(PORT));
        cons = new HashSet<WebSocket>();
    }

    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        cons.add(webSocket); //add to set
        System.out.println("New connection from: " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());
        webSocket.send("Hello!");
        String info = webSocket.getResourceDescriptor();
        String token = info.split("/")[2];
        if (!token.isEmpty()) {
            String name = token.split("&")[0];
            String pass = token.split("&")[1];

            if (users.containsKey(name) && users.get(name).equals(pass) && authUsers.get(name)) {
                webSocket.send("Hello, " + name + "!");
            } else {
                webSocket.send("Hello, u need to log in!");
            }
        } else {
            webSocket.send("Hello, new user!");
        }
    }


    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        cons.remove(webSocket); //delete from set
        System.out.println("Closed connection to: " + webSocket.getRemoteSocketAddress().getAddress().getHostAddress());

    }

    public void onMessage(WebSocket webSocket, String message) {
        String filepath = "C:\\Users\\alexa\\IdeaProjects\\aacom.WebSocket\\src\\main\\java\\userinfo.txt";
        String ipdir = "C:\\Users\\alexa\\IdeaProjects\\aacom.WebSocket\\src\\main\\java\\userip.txt";

        JSONObject obj = JSON.parseObject(message);
        String type = obj.getString("type");
        String user = obj.getString("name");
        String pass = obj.getString("pass");
        //System.out.println("type: " + type + "\nuser: " + user + "\npass: " + pass);
        if (type.equals("registration")) {
            if (!users.containsKey("user")) {
                users.put(user, pass);
                authUsers.put(user, true);
                webSocket.send("{newKey:" + user + "%" + pass + "}");
            } else {
                webSocket.send("User Already Exists");
            }
        } else if (type.equals("login")) {
            if (users.containsKey(user)) {
                if (users.get(user).equals(pass)) {
                    authUsers.put(user, true);
                    webSocket.send("Logged in Successfully");
                } else {
                    webSocket.send("Wrong Password");
                }

            } else {
                webSocket.send("User Invalid");
            }

        }


    }

    public void onError (WebSocket webSocket, Exception e){

    }

    public void onStart () {

    }

}
