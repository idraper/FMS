package Handlers;

import Request.LoadRequest;
import Result.MsgResult;
import Service.LoadService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A class to handle a web Load POST event.
 */
public class LoadHandler extends PostHandler implements HttpHandler {

    /**
     * Callback function to catch a POST load event.
     * @param exchange An HttpExchange object for web handling.
     */
    public void handle(HttpExchange exchange) throws IOException {
        LoadService service = new LoadService();

        if (!exchange.getRequestMethod().equals("POST")) {
            exchange.sendResponseHeaders(400,0);
        }

        Gson gson = new GsonBuilder().create();
        MsgResult result = service.load(gson.fromJson(getBody(exchange.getRequestBody()), LoadRequest.class));

        OutputStream os = exchange.getResponseBody();

        byte[] s = gson.toJson(result).getBytes();

        exchange.getResponseHeaders().set("Content-type", "application/json");
        exchange.sendResponseHeaders(200, s.length);

        os.write(s);
        os.close();
    }
}
