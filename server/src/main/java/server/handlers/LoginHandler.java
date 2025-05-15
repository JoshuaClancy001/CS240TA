package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import server.requests.LoginRequest;
import server.requests.RegisterRequest;
import server.results.AuthResult;
import server.service.LoginService;
import server.service.RegisterService;
import spark.Request;
import spark.Response;

public class LoginHandler extends Handler{
    @Override
    public String handleTry(Gson gson, Request req, Response res, DaoFactory daoFactory) throws DataAccessException {
        LoginRequest request = gson.fromJson(req.body(),LoginRequest.class);

        AuthResult result = new LoginService().getResult(request, daoFactory);

        res.body((gson.toJson(result)));
        res.status(200);
        return gson.toJson(result);
    }
}
