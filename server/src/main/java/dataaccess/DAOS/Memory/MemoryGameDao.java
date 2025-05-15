package dataaccess.DAOS.Memory;

import chess.ChessGame;
import dataaccess.DAOS.GameDao;
import dataaccess.DataAccessException;
import model.GameData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MemoryGameDao implements GameDao {
    private int gameID = 1;
    private final HashMap<Integer, GameData> games = new HashMap<Integer,GameData>();
    private HashMap<String, Integer> gameIds = new HashMap<>();
    @Override
    public int putGame(String gameName) throws DataAccessException {
        if (gameIds.get(gameName) != null){
            throw new DataAccessException("bad request", 400);
        }
        if (gameName == null){
            throw new DataAccessException("bad request", 400);
        }
        gameIds.put(gameName, gameID);
        GameData game = new GameData(gameID,null,null,gameName,new ChessGame());
        games.put(gameID,game);
        gameID += 1;
        return gameID - 1;
    }

    @Override
    public GameData getGame(Integer gameID) throws DataAccessException {
        if (games.get(gameID) == null){
            throw new DataAccessException("bad request", 400);
        }
        return games.get(gameID);
    }

    @Override
    public void updateGame(Integer gameID, GameData gameData) throws DataAccessException {
        if (games.get(gameID) == null){
            throw new DataAccessException("bad request", 400);
        }
        games.put(gameID, gameData);

    }

    @Override
    public Collection<GameData> getAllGames() {
        return new ArrayList<>(games.values());
    }

    @Override
    public void clearGames() {
        this.games.clear();
        this.gameIds.clear();
    }


}
