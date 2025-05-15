package dataaccess.DAOS.Memory;

import dataaccess.DAOS.AuthDao;
import dataaccess.DataAccessException;
import model.AuthData;

import java.util.HashMap;
import java.util.UUID;

public class MemoryAuthDao implements AuthDao {

    private HashMap<String, AuthData> auths = new HashMap<String, AuthData>();


    @Override
    public String createAuth(String username) throws DataAccessException {
        String authToken = UUID.randomUUID().toString();
        AuthData authData = new AuthData(authToken, username);

        auths.put(authData.authToken(),authData);

        return authToken;
    }

    @Override
    public AuthData readAuth(String authToken) throws DataAccessException {
        AuthData data = auths.get(authToken);
        if (data == null){
            throw new DataAccessException("unauthorized", 401);
        }
        else{
            return data;
        }
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {
        AuthData authData = auths.get(authToken);
        if (authData == null){
            throw new DataAccessException("unauthorized", 401);
        }
        auths.remove(authToken);
    }

    @Override
    public void clearAuth() throws DataAccessException {
        this.auths.clear();
    }
}
