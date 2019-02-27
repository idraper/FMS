package Handlers;

import Request.EventRequest;
import Result.MsgResult;
import Service.EventService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A class to handle a web Event GET event.
 */
public class EventHandler implements HttpHandler {

    /**
     * Callback function to catch a GET Event event.
     * @param exchange An HttpExchange object for web handling.
     */
    public void handle(HttpExchange exchange) throws IOException {
        EventService service = new EventService();

        if (!exchange.getRequestMethod().equals("GET")) {
            exchange.sendResponseHeaders(400,0);
        }

        Gson gson = new GsonBuilder().create();
        OutputStream os = exchange.getResponseBody();

        String[] pathSplit = exchange.getRequestURI().toString().split("/");

        MsgResult result = null;
        if (pathSplit.length > 2) {
            result = service.event(gson.fromJson("{'eventID':'" + exchange.getRequestURI().toString().split("/")[2] + "','token':'" + exchange.getRequestHeaders().get("token").get(0) + "'}", EventRequest.class));
        }
        else {
            result = service.events(gson.fromJson("{'token':'" + exchange.getRequestHeaders().get("token").get(0) + "'}", EventRequest.class));
        }

        byte[] s = gson.toJson(result).getBytes();

        exchange.getResponseHeaders().set("Content-type", "application/json");
        exchange.sendResponseHeaders(200, s.length);

        os.write(s);
        os.close();
    }
}
