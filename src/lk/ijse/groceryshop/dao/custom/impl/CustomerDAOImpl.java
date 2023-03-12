package lk.ijse.groceryshop.dao.custom.impl;

import lk.ijse.groceryshop.dao.custom.CustomerDAO;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.entity.Item;
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


    public CustomerDAOImpl(Connection connection){
        this.connection=connection;
    }

    @Override
    public boolean save(Customer entity , Session session) {
        try {
            session.save(entity);
            return true;
        } catch (HibernateException e) {
            return false;
        }

    }

    @Override
    public boolean update(Customer entity , Session session) {
        try {
            session.update(entity);
            return true;
        } catch (HibernateException e) {
            return false;
        }

    }

    @Override
    public boolean deleteByPk(String pk, Session session) {
        try {
            Customer c=session.load(Customer.class,pk);  //---------load
            session.delete(c);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public Customer findByPk(String pk, Session session) {  //meka implement krnnh
        try{
            return session.get(Customer.class,pk);
        }catch(HibernateException e){
            throw new RuntimeException("Failed to find the Item");
        }
    }

    @Override
    public boolean existByPk(String pk) {
        Session session ;
        Transaction transaction ;
        Customer c=null;
        session = null;
        transaction = null;
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        try {
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
    public List<Customer> SearchCustomersByTesxt(String text, Session session) {  //HQL wlin krnna

        List<Customer> list = new ArrayList<>();

        text="%"+text+"%";

        Criteria criteria = session.createCriteria(Customer.class)
                .add(Restrictions.like("id", text));
        list.addAll(criteria.list());

        System.out.println(list+"");

        return list;
        
    }

    @Override
    public List<String> SearchCustomerAllIds(Session session) {
        return session.createQuery("select c.id from Customer c").list();
    }
}
