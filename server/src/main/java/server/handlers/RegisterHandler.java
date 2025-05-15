package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import server.requests.RegisterRequest;
import server.results.AuthResult;
import server.service.RegisterService;
import spark.Request;
import spark.Response;

public class RegisterHandler extends Handler{

    private static RegisterHandler instance;

    @Override
    public String handleTry(Gson gson, Request req, Response res, DaoFactory daoFactory) throws DataAccessException {
        RegisterRequest request = gson.fromJson(req.body(),RegisterRequest.class);

        AuthResult result = new RegisterService().getResult(request, daoFactory);

        res.body((gson.toJson(result)));
        res.status(200);
        return gson.toJson(result);

    }

    public static RegisterHandler getInstance(){
        if(instance == null){
            return new RegisterHandler();
        }
        else{
            return instance;
        }
    }
}
