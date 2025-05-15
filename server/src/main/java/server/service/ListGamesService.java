package server.service;

import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import model.AuthData;
import server.requests.AuthedRequest;
import server.results.ListGamesResult;

public class ListGamesService extends Service<AuthedRequest, ListGamesResult> {
    @Override
    public ListGamesResult getResult(AuthedRequest authedRequest, DaoFactory daoFactory) throws DataAccessException {
        AuthData authData = daoFactory.getAuthDao().readAuth(authedRequest.getAuthToken());
        if (authData == null){
            throw new DataAccessException("unauthorized", 401);
        }
        return new ListGamesResult(daoFactory.getGameDao().getAllGames());

    }
}
