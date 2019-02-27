package Handlers;

import Request.RegisterRequest;
import Result.MsgResult;
import Service.RegisterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A class to handle a web Register POST event.
 */
public class RegisterHandler extends PostHandler implements HttpHandler {

    /**
     * Callback function to catch a POST register event.
     * @param exchange An HttpExchange object for web handling.
     */
    public void handle(HttpExchange exchange) throws IOException {
        RegisterService service = new RegisterService();

        if (!exchange.getRequestMethod().equals("POST")) {
            exchange.sendResponseHeaders(400,0);
        }

        Gson gson = new GsonBuilder().create();
        OutputStream os = exchange.getResponseBody();

        MsgResult result = service.register(gson.fromJson(getBody(exchange.getRequestBody()), RegisterRequest.class));

        byte[] s = gson.toJson(result).getBytes();

        exchange.getResponseHeaders().set("Content-type", "application/json");
        exchange.sendResponseHeaders(200, s.length);

        os.write(s);
        os.close();
    }
}
