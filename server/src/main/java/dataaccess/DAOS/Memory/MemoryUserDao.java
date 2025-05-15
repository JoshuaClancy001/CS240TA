package dataaccess.DAOS.Memory;

import dataaccess.DAOS.UserDao;
import dataaccess.DataAccessException;
import model.UserData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MemoryUserDao implements UserDao {

    private HashMap<String, UserData> users = new HashMap<String,UserData>();

    @Override
    public void putUser(UserData user) throws DataAccessException{
        if(user.password() == null){
            throw new DataAccessException("bad request", 400);
        }
        if (users.get(user.username()) != null){
            throw new DataAccessException("already taken", 403 );
        }
        users.put(user.username(), user);
    }

    @Override
    public UserData getUser(String username, String password) throws DataAccessException {
        if (username == null | password == null){
            throw new DataAccessException("bad request", 400);
        }

        UserData user = users.get(username);
        if (user == null){
            throw new DataAccessException("unauthorized", 401);
        }
        if (!user.password().equals(password)){
            throw new DataAccessException("unauthorized", 401);
        }
        return user;
    }

    @Override
    public void clearUsers() {
        this.users.clear();
    }

}
