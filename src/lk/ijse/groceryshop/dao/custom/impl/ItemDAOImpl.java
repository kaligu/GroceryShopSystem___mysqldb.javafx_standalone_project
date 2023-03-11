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
    public ItemDAOImpl(Connection connection){
        this.connection=connection;
    }

    @Override
    public boolean save(Item entity , Session session) {
        try {
            session.save(entity);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean update(Item entity , Session session) {
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
            Item c=session.load(Item.class,pk);
            session.delete(c);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public Optional<Item> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        return true;
    }

    @Override
    public List<Item> SearchItemsByTesxt(String text , Session session) { //hql wlin

        List<Item> list = new ArrayList<>();

        text="%"+text+"%";

        Criteria criteria = session.createCriteria(Item.class)
                .add(Restrictions.like("code", text));
        list.addAll(criteria.list());

        System.out.println(list+"");

        return list;
    }

    @Override
    public List<String> SearchItemAllIds(Session session) {
        return session.createQuery("select i.id from Item i").list();
    }
}
