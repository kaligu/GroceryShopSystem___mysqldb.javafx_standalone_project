package lk.ijse.groceryshop.dao.custom.impl;

import lk.ijse.groceryshop.dao.custom.ItemDAO;
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
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ItemDAOImpl implements ItemDAO {
    private final Connection connection;
    Session session ;
    Transaction transaction ;

    public ItemDAOImpl(Connection connection){
        this.connection=connection;
    }

    @Override
    public boolean save(Item entity) {
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
    public boolean update(Item entity) {
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
            Item c=session.get(Item.class,pk);
            transaction.commit();
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Item> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        Item c=null;
        session = null;
        transaction = null;
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        try {

            session = null;
            transaction = null;
            session = HbFactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            c = session.get(Item.class,pk);

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
    public Collection<? extends Item> SearchItemsByTesxt(String text) {
        session = null;
        transaction = null;
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        List<Item> list = new ArrayList<>();

        text="%"+text+"%";

        try {

            Criteria criteria = session.createCriteria(Item.class)
                    .add(Restrictions.like("description", text));
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
