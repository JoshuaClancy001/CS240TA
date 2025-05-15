package dataaccess.DAOS;

import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;

public interface AuthDao {
    public String createAuth(String username) throws DataAccessException;

    public AuthData readAuth(String authToken) throws DataAccessException;

    public void deleteAuth(String authToken) throws DataAccessException;

    public void clearAuth() throws DataAccessException;
}
