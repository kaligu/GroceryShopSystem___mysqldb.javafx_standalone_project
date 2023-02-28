package lk.ijse.groceryshop.dao.custom.impl;

import lk.ijse.groceryshop.dao.custom.CustomerDAO;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.util.HbFactoryConfiguration;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    private final Connection connection;
    Session session ;
    Transaction transaction ;

    public CustomerDAOImpl(Connection connection){
        this.connection=connection;
    }

    @Override
    public boolean save(Customer entity) {
        session = null;
        transaction = null;
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        try {
            session.save(entity);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public boolean update(Customer entity) {
        session = null;
        transaction = null;
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        try {
            session.update(entity);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public void deleteByPk(String pk) {
        session = null;
        transaction = null;
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        try {
            Customer c=session.get(Customer.class,pk);
            transaction.commit();
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Customer> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        Customer c=null;
        session = null;
        transaction = null;
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        try {

            session = null;
            transaction = null;
            session = HbFactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            c = session.get(Customer.class,pk);

            transaction.commit();
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
        } finally {
            session.close();
        }
        if(c!=null){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public List<Customer> SearchCustomersByTesxt(String text) {
        session = null;
        transaction = null;
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        List<Customer> list = new ArrayList<>();

        text="%"+text+"%";

        try {

            Criteria criteria = session.createCriteria(Customer.class)
                    .add(Restrictions.like("name", text));
            list.addAll(criteria.list());

            transaction.commit();
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return list;
        
    }
}
