package server.service;

import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import model.AuthData;
import model.UserData;
import server.requests.RegisterRequest;
import server.results.AuthResult;

import java.util.UUID;

public class RegisterService extends Service<RegisterRequest, AuthResult> {

    @Override
    public AuthResult getResult(RegisterRequest registerRequest, DaoFactory daoFactory) throws DataAccessException {

        UserData user =  new UserData(registerRequest.getUsername(),registerRequest.getPassword(), registerRequest.getEmail());
        daoFactory.getUserDao().putUser(user);

        String authToken = daoFactory.getAuthDao().createAuth(registerRequest.getUsername());
        return new AuthResult(user.username(),authToken);
    }
}
