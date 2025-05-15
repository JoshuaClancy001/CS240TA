package dataaccess.Factory;

import dataaccess.DAOS.AuthDao;
import dataaccess.DAOS.GameDao;
import dataaccess.DAOS.Memory.MemoryAuthDao;
import dataaccess.DAOS.Memory.MemoryGameDao;
import dataaccess.DAOS.Memory.MemoryUserDao;
import dataaccess.DAOS.UserDao;
import model.AuthData;

import java.util.HashMap;

public class MemoryDaoFactory implements DaoFactory{
    private UserDao userDao = new MemoryUserDao();
    private AuthDao authDao = new MemoryAuthDao();
    private GameDao gameDao = new MemoryGameDao();


    @Override
    public UserDao getUserDao() {
        return this.userDao;
    }

    @Override
    public AuthDao getAuthDao() {
        return this.authDao;
    }

    @Override
    public GameDao getGameDao() {
        return this.gameDao;
    }
}
