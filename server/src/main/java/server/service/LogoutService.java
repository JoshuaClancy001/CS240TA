package server.service;

import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import server.requests.LogoutRequest;

public class LogoutService extends Service<LogoutRequest,Void>{
    @Override
    public Void getResult(LogoutRequest logoutRequest, DaoFactory daoFactory) throws DataAccessException {
        if (logoutRequest == null){
            throw new DataAccessException("unauthorized", 401);
        }
        daoFactory.getAuthDao().deleteAuth(logoutRequest.getAuthToken());
        return null;
    }
}
