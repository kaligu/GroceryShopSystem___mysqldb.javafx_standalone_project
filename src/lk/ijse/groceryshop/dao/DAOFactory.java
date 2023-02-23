package lk.ijse.groceryshop.dao;

import lk.ijse.groceryshop.dao.custom.impl.CustomerDAOImpl;
import lombok.Builder;

import java.sql.Connection;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getInstance(){return daoFactory==null? (daoFactory=new DAOFactory()) : daoFactory;}

    public <T> T getDAO(Connection connection , DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return (T)new CustomerDAOImpl(connection);
            default:
                return null;
        }
    }
}
