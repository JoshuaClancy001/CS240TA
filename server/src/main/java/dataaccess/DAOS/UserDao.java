package dataaccess.DAOS;

import dataaccess.DataAccessException;
import model.UserData;

public interface UserDao {
    public void putUser(UserData user) throws DataAccessException;

    public UserData getUser(String username, String password) throws DataAccessException;

    public void clearUsers();
}
