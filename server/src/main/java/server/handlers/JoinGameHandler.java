package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import server.requests.JoinGameRequest;
import server.results.JoinGameResult;
import server.service.JoinGameService;
import spark.Request;
import spark.Response;

public class JoinGameHandler extends Handler{
    @Override
    public String handleTry(Gson gson, Request req, Response res, DaoFactory daoFactory) throws DataAccessException {
        JoinGameRequest request = gson.fromJson(req.body(), JoinGameRequest.class);

        String auth = req.headers("Authorization");
        JoinGameRequest joinGameRequest = new JoinGameRequest(auth,request.playerColor(), request.gameID());

        JoinGameResult joinGameResult = new JoinGameService().getResult(joinGameRequest,daoFactory);

        res.body((gson.toJson(joinGameResult)));
        res.status(200);
        return gson.toJson(joinGameResult);
    }
}
