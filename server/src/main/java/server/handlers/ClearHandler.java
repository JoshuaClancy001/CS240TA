package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import server.requests.LogoutRequest;
import server.service.ClearService;
import spark.Request;
import spark.Response;

public class ClearHandler extends Handler{
    @Override
    public String handleTry(Gson gson, Request req, Response res, DaoFactory daoFactory) throws DataAccessException {
        LogoutRequest request = new LogoutRequest(req.headers("Authorization"));
        new ClearService().getResult(request,daoFactory);
        res.status(200);
        return "";
    }
}
