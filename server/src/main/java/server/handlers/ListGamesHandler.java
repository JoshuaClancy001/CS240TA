package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import server.requests.AuthedRequest;
import server.requests.JoinGameRequest;
import server.results.JoinGameResult;
import server.results.ListGamesResult;
import server.service.JoinGameService;
import server.service.ListGamesService;
import spark.Request;
import spark.Response;

public class ListGamesHandler extends Handler{
    @Override
    public String handleTry(Gson gson, Request req, Response res, DaoFactory daoFactory) throws DataAccessException {
        AuthedRequest request = gson.fromJson(req.body(), AuthedRequest.class);

        String auth = req.headers("Authorization");
        AuthedRequest listGamesRequest = new AuthedRequest(auth);

        ListGamesResult listGamesResult = new ListGamesService().getResult(listGamesRequest,daoFactory);

        res.body((gson.toJson(listGamesResult)));
        res.status(200);
        return gson.toJson(listGamesResult);
    }
}
