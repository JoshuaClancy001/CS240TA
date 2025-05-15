package dataaccess.DAOS;

import dataaccess.DataAccessException;
import model.GameData;

import java.util.Collection;

public interface GameDao {
    int putGame(String gameName) throws DataAccessException;
    GameData getGame(Integer gameID) throws DataAccessException;
    void updateGame(Integer gameID, GameData gameData) throws DataAccessException;
    Collection<GameData> getAllGames();
    void clearGames();
}
