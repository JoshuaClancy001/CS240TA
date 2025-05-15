package server.service;

import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import server.requests.LogoutRequest;

public class ClearService extends Service<LogoutRequest,Void> {
    @Override
    public Void getResult(LogoutRequest logoutRequest, DaoFactory daoFactory) throws DataAccessException {
        if (logoutRequest == null){
            throw new DataAccessException("unauthorized", 401);
        }
        daoFactory.getAuthDao().clearAuth();
        daoFactory.getUserDao().clearUsers();
        daoFactory.getGameDao().clearGames();
        return null;
    }
}
