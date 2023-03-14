package lk.ijse.groceryshop.dao.custom.impl;

import lk.ijse.groceryshop.dao.custom.OrderDetailsDAO;
import lk.ijse.groceryshop.entity.OrderDetails;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.sql.Connection;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    private final Connection connection;


    public OrderDetailsDAOImpl(Connection connection){
        this.connection=connection;
    }

    @Override
    public boolean save(OrderDetails entity, Session session) {
        try {
            session.save(entity);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean update(OrderDetails entity, Session session) {
        return false;
    }

    @Override
    public boolean deleteByPk(String pk, Session session) {
        return false;
    }

    @Override
    public OrderDetails findByPk(String pk, Session session) {
        return null;
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public OrderDetails saveAndGet(OrderDetails od, Session session) {
        try {
            session.save(od);
            return session.get(OrderDetails.class,od);
        } catch (HibernateException e) {
            return null;
        }
    }
}
