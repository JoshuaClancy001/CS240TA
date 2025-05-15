package server;

import dataaccess.Factory.DaoFactory;
import dataaccess.Factory.MemoryDaoFactory;
import server.handlers.*;
import spark.*;

public class Server {

    private DaoFactory daoFactory;

    public int run(int desiredPort) {

        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        daoFactory = new MemoryDaoFactory();

        Spark.post("/user", ((request, response) ->
                (RegisterHandler.getInstance().handleRequest(request, response, daoFactory))));

        Spark.post("/session", (((request, response) ->
                (new LoginHandler().handleRequest(request,response,daoFactory)))));

        Spark.delete("/session", ((request, response) ->
                (new LogoutHandler().handleRequest(request,response,daoFactory))));

        Spark.post("/game", (((request, response) ->
                new CreateGameHandler().handleRequest(request,response,daoFactory))));

        Spark.put("/game", (((request, response) ->
                new JoinGameHandler().handleRequest(request,response,daoFactory))));

        Spark.get("/game", (((request, response) ->
                new ListGamesHandler().handleRequest(request,response,daoFactory))));

        Spark.delete("/db", (((request, response) ->
                (new ClearHandler().handleRequest(request,response,daoFactory))
                )));

        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
