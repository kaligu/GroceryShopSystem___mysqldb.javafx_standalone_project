package lk.ijse.groceryshop.dao;

import lk.ijse.groceryshop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.groceryshop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.groceryshop.dao.custom.impl.OrderDAOImpl;
import lk.ijse.groceryshop.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.groceryshop.service.custom.Impl.ItemServiceImpl;
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
            case ITEM:
                return (T)new ItemDAOImpl(connection);
            case ORDER:
                return (T)new OrderDAOImpl(connection);
            case ORDERDETAILS:
                return (T)new OrderDetailsDAOImpl(connection);
            default:
                return null;
        }
    }
}
