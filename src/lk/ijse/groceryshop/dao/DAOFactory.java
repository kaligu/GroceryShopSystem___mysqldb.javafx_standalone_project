package lk.ijse.groceryshop.dao;

import java.sql.Connection;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getInstance(){return daoFactory==null? (daoFactory=new DAOFactory()) : daoFactory;}

    public <T> T getDAO(Connection connection , DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new
        }
    }
}
