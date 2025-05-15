package server.service;

import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import model.AuthData;
import server.requests.CreateGameRequest;
import server.results.CreateGameResult;

public class CreateGameService extends Service<CreateGameRequest,CreateGameResult> {
    @Override
    public CreateGameResult getResult(CreateGameRequest createGameRequest, DaoFactory daoFactory) throws DataAccessException {
        AuthData authData = daoFactory.getAuthDao().readAuth(createGameRequest.authToken());
        int gameID = daoFactory.getGameDao().putGame(createGameRequest.gameName());
        return new CreateGameResult(gameID);
    }
}
