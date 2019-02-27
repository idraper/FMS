package Handlers;

import Request.LoginRequest;
import Request.RegisterRequest;
import Result.LoginResult;
import Result.MsgResult;
import Result.RegisterResult;
import Service.LoginService;
import Service.RegisterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A class to handle a web Login POST event.
 */
public class LoginHandler extends PostHandler implements HttpHandler {

    /**
     * Callback function to catch a POST login event.
     * @param exchange An HttpExchange object for web handling.
     */
    public void handle(HttpExchange exchange) throws IOException {
        LoginService service = new LoginService();

        if (!exchange.getRequestMethod().equals("POST")) {
            exchange.sendResponseHeaders(400,0);
        }

        Gson gson = new GsonBuilder().create();
        OutputStream os = exchange.getResponseBody();

        MsgResult result = service.login(gson.fromJson(getBody(exchange.getRequestBody()), LoginRequest.class));

        byte[] s = gson.toJson(result).getBytes();

        exchange.getResponseHeaders().set("Content-type", "application/json");
        exchange.sendResponseHeaders(200, s.length);

        os.write(s);
        os.close();
    }
}
