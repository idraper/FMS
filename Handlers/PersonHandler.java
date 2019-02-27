package Handlers;

import Request.PersonRequest;
import Result.MsgResult;
import Service.PersonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * A class to handle a web Person GET event.
 */
public class PersonHandler extends PostHandler implements HttpHandler {

    /**
     * Callback function to catch a GET person event.
     * @param exchange An HttpExchange object for web handling.
     */
    public void handle(HttpExchange exchange) throws IOException {
        PersonService service = new PersonService();

        if (!exchange.getRequestMethod().equals("GET")) {
            exchange.sendResponseHeaders(400,0);
        }

        Gson gson = new GsonBuilder().create();
        OutputStream os = exchange.getResponseBody();

        String[] pathSplit = exchange.getRequestURI().toString().split("/");

        MsgResult result = null;
        if (pathSplit.length > 2) {
            result = service.person(gson.fromJson("{'personID':'" + exchange.getRequestURI().toString().split("/")[2] + "','token':'" + exchange.getRequestHeaders().get("token").get(0) + "'}", PersonRequest.class));
        }
        else {
            result = service.people(gson.fromJson("{'token':'" + exchange.getRequestHeaders().get("token").get(0) + "'}", PersonRequest.class));
        }

        byte[] s = gson.toJson(result).getBytes();

        exchange.getResponseHeaders().set("Content-type", "application/json");
        exchange.sendResponseHeaders(200, s.length);

        os.write(s);
        os.close();
    }
}
