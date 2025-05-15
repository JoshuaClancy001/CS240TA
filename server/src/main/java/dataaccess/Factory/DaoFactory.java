package dataaccess.Factory;

import dataaccess.DAOS.AuthDao;
import dataaccess.DAOS.GameDao;
import dataaccess.DAOS.UserDao;

public interface DaoFactory {
    UserDao getUserDao();
    AuthDao getAuthDao();
    GameDao getGameDao();
}
