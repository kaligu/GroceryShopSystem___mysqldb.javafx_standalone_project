package lk.ijse.groceryshop.dao.custom.impl;

import lk.ijse.groceryshop.dao.custom.CustomerDAO;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.util.HbFactoryConfiguration;
import org.hibernate.Criteria;
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

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Customer entity) {
        session = null;
        transaction = null;
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public void deleteByPk(String pk) {
        session = null;
        transaction = null;
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        Customer c=session.get(Customer.class,pk);
        session.delete(c);

        transaction.commit();
        session.close();
    }

    @Override
    public Optional<Customer> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        return true;
    }

    @Override
    public List<Customer> SearchCustomersByTesxt(String text) {
        session = null;
        transaction = null;
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        List<Customer> list = new ArrayList<>();

        text="%"+text+"%";

        Criteria criteria = session.createCriteria(Customer.class)
                .add(Restrictions.like("name", text));
        list.addAll(criteria.list());


        transaction.commit();
        session.close();

        return list;
        
    }
}
