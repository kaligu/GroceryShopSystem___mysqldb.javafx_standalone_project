package lk.ijse.groceryshop.dao.custom.impl;

import lk.ijse.groceryshop.dao.custom.OrderDAO;
import lk.ijse.groceryshop.entity.Order;
import lk.ijse.groceryshop.service.exception.NotFoundException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Connection;

public class OrderDAOImpl implements OrderDAO {
    private final Connection connection;


    public OrderDAOImpl(Connection connection){
        this.connection=connection;
    }

    @Override
    public boolean save(Order entity, Session session) {
        try {
            session.save(entity);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean update(Order entity, Session session) {
        try {
            session.update(entity);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean deleteByPk(String pk, Session session) {
        return false;
    }

    @Override
    public Order findByPk(String pk, Session session) {
        return null;
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public String getLastorderID(Session session) {
        String id="null";
        try {
            String hql = "FROM Order f ORDER BY f.id DESC, f.id DESC";
            Query query = session.createQuery(hql);
            Order o = (Order) query.getResultList().get(0);
            id=o.getOrderId();
        } catch (HibernateException e) {
            id="null";
            throw new NotFoundException("Failed to find the book details");
        }

        return id;
    }
}
