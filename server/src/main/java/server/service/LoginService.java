package server.service;

import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import model.UserData;
import server.requests.LoginRequest;
import server.results.AuthResult;

public class LoginService extends Service<LoginRequest, AuthResult> {

    @Override
    public AuthResult getResult(LoginRequest loginRequest, DaoFactory daoFactory) throws DataAccessException {
        UserData user = daoFactory.getUserDao().getUser(loginRequest.getUsername(), loginRequest.getPassword());

        String authToken = daoFactory.getAuthDao().createAuth(loginRequest.getUsername());

        return new AuthResult(user.username(),authToken);

    }
}
