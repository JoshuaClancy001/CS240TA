package server.service;
import dataaccess.DataAccessException;
import dataaccess.Factory.DaoFactory;
import dataaccess.Factory.MemoryDaoFactory;

abstract public class Service<Request,Result> {
    protected DaoFactory daoFactory;

    protected abstract Result getResult(Request request, DaoFactory daoFactory) throws DataAccessException;
}


