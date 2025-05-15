package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import server.requests.CreateGameRequest;
import server.results.CreateGameResult;
import server.service.CreateGameService;
import spark.Request;
import spark.Response;

public class CreateGameHandler extends Handler{
    @Override
    public String handleTry(Gson gson, Request req, Response res, DaoFactory daoFactory) throws DataAccessException {
        CreateGameRequest request = gson.fromJson(req.body(), CreateGameRequest.class);

        String auth = req.headers("Authorization");
        CreateGameRequest createGameRequest = new CreateGameRequest(auth,request.gameName());

        CreateGameResult result = new CreateGameService().getResult(createGameRequest, daoFactory);

        res.body((gson.toJson(result)));
        res.status(200);
        return gson.toJson(result);
    }
}
