package dataaccess.Factory;

import dataaccess.DAOS.AuthDao;
import dataaccess.DAOS.GameDao;
import dataaccess.DAOS.UserDao;

public class SQLDaoFactory implements DaoFactory{
    @Override
    public UserDao getUserDao() {
        return null;
    }

    @Override
    public AuthDao getAuthDao() {
        return null;
    }

    @Override
    public GameDao getGameDao() {
        return null;
    }
}
