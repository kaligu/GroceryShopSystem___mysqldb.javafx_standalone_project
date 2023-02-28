package lk.ijse.groceryshop.util;

import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.hibernate.cfg.Configuration;

public class HbFactoryConfiguration {

    private static HbFactoryConfiguration hbFactoryConfiguration;
    private SessionFactory sessionFactory;

    private HbFactoryConfiguration(){
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class);
        sessionFactory = configuration.buildSessionFactory();
    }
    public static HbFactoryConfiguration getInstance(){
        return (hbFactoryConfiguration == null) ? hbFactoryConfiguration = new HbFactoryConfiguration() : hbFactoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
