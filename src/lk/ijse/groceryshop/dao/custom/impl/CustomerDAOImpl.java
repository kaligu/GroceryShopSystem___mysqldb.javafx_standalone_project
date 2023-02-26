package lk.ijse.groceryshop.dao.custom.impl;

import lk.ijse.groceryshop.dao.custom.CustomerDAO;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.util.HbFactoryConfiguration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.Root;
import java.lang.management.MonitorInfo;
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
    public Customer save(Customer entity) {
        return null;
    }

    @Override
    public Customer update(Customer entity) {
        return null;
    }

    @Override
    public void deleteByPk(String pk) {

    }

    @Override
    public Optional<Customer> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public List<Customer> SearchCustomersByTesxt(String text) {
/*
*/      List<Customer> list = new ArrayList<>();
        Session session = HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        text="%"+text+"%";

        Criteria criteria = session.createCriteria(Customer.class)
                .add(Restrictions.like("name", text));
        list.addAll(criteria.list());


        transaction.commit();
        session.close();

        return list;
        
    }
}
