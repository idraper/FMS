package Handlers;

import Request.FillRequest;
import Result.FillResult;
import Result.MsgResult;
import Service.FillService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * A class to handle a web Fill POST event.
 */
public class FillHandler implements HttpHandler {

    /**
     * Callback function to catch a POST Fill event.
     * @param exchange An HttpExchange object for web handling.
     */
    public void handle(HttpExchange exchange) throws IOException {
        FillService service = new FillService();

        if (!exchange.getRequestMethod().equals("POST")) {
            exchange.sendResponseHeaders(400,0);
        }

        String[] pathSplit = exchange.getRequestURI().toString().split("/");

        MsgResult result;
        if (pathSplit.length < 4)
            result = service.fill(new FillRequest(pathSplit[2]));
        else
            result = service.fill(new FillRequest(pathSplit[2], Integer.parseInt(pathSplit[3])));

        Gson gson = new GsonBuilder().create();
        OutputStream os = exchange.getResponseBody();

        byte[] s = gson.toJson(result).getBytes();

        exchange.getResponseHeaders().set("Content-type", "application/json");
        exchange.sendResponseHeaders(200, s.length);

        os.write(s);
        os.close();
    }
}
