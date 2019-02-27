package Handlers;

import Result.MsgResult;
import Service.ClearService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

/**
 * A class to handle a web Clear POST event.
 */
public class ClearHandler implements HttpHandler {

    /**
     * Callback function to catch a POST clear event.
     * @param exchange An HttpExchange object for web handling.
     */
    public void handle(HttpExchange exchange) throws IOException {
        ClearService service = new ClearService();
        MsgResult result = service.clear();

        if (!exchange.getRequestMethod().equals("GET")) {
            exchange.sendResponseHeaders(400,0);
        }

        Gson gson = new GsonBuilder().create();
        OutputStream os = exchange.getResponseBody();

        byte[] s = gson.toJson(result).getBytes();

        exchange.getResponseHeaders().set("Content-type", "application/json");
        exchange.sendResponseHeaders(200, s.length);

        os.write(s);
        os.close();
    }
}
