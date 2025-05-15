package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import spark.Request;
import spark.Response;

abstract public class Handler{



    public String handleRequest(Request req, Response res, DaoFactory daoFactory){
        Gson gson = new Gson();
        try{
            return this.handleTry(gson,req, res, daoFactory);
        }
        catch (DataAccessException e){
            res.status(e.status);
            return "{ \"message\": \"Error: " + e.getMessage() + "\" }";
        }
    }
    public abstract String handleTry(Gson gson, Request req, Response res, DaoFactory daoFactory) throws DataAccessException;

}
