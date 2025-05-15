package server.service;

import dataaccess.DAOS.GameDao;
import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import model.AuthData;
import model.GameData;
import server.requests.JoinGameRequest;
import server.results.JoinGameResult;

public class JoinGameService extends Service<JoinGameRequest, JoinGameResult> {
    @Override
    public JoinGameResult getResult(JoinGameRequest joinGameRequest, DaoFactory daoFactory) throws DataAccessException {
        AuthData authData = daoFactory.getAuthDao().readAuth(joinGameRequest.authToken());
        if (authData == null){
            throw new DataAccessException("unauthorized", 401);
        }
        String color = joinGameRequest.playerColor();
        if (color == null || (!color.equals("WHITE") && !color.equals("BLACK"))) {
            throw new DataAccessException("bad request", 400);
        }
        GameData newGame;
        GameData game = daoFactory.getGameDao().getGame(joinGameRequest.gameID());
        if (joinGameRequest.playerColor().equals("WHITE")){
            if (game.whiteUsername() != null){
                throw new DataAccessException("already taken", 403);
            }
            newGame = new GameData(game.gameID(), authData.username(), game.blackUsername(),game.gameName(),game.game());
        }
        else{
            if (game.blackUsername() != null){
                throw new DataAccessException("already taken", 403);
            }
            newGame = new GameData(game.gameID(), game.whiteUsername(), authData.username(),game.gameName(),game.game());
        }
        daoFactory.getGameDao().updateGame(game.gameID(), newGame);
        return new JoinGameResult(joinGameRequest.playerColor(), game.gameID());
    }
}
