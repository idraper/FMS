import Handlers.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FMServer {

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);

        InetSocketAddress addr = new InetSocketAddress(port);
        System.out.println("Started server on port " + port + " ...");

        HttpServer s1 = HttpServer.create(addr, 0);

        s1.createContext("/clear", new ClearHandler());
        s1.createContext("/fill", new FillHandler());
        s1.createContext("/register", new RegisterHandler());
        s1.createContext("/login", new LoginHandler());
        s1.createContext("/person", new PersonHandler());
        s1.createContext("/event", new EventHandler());
        s1.createContext("/load", new LoadHandler());

        ExecutorService executor = Executors.newCachedThreadPool();
        s1.setExecutor(executor);
        s1.start();
    }
}
