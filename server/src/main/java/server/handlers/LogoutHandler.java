package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import server.requests.LoginRequest;
import server.requests.LogoutRequest;
import server.requests.RegisterRequest;
import server.results.AuthResult;
import server.service.LogoutService;
import server.service.RegisterService;
import spark.Request;
import spark.Response;

public class LogoutHandler extends Handler{
    @Override
    public String handleTry(Gson gson, Request req, Response res, DaoFactory daoFactory) throws DataAccessException {
        LogoutRequest request = new LogoutRequest(req.headers("Authorization"));
        new LogoutService().getResult(request, daoFactory);
        res.status(200);
        return "";
    }
}
