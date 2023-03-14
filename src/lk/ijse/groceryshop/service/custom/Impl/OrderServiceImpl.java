package lk.ijse.groceryshop.service.custom.Impl;

import lk.ijse.groceryshop.dao.DAOFactory;
import lk.ijse.groceryshop.dao.DAOTypes;
import lk.ijse.groceryshop.dao.custom.ItemDAO;
import lk.ijse.groceryshop.dao.custom.OrderDAO;
import lk.ijse.groceryshop.db.DBConnection;
import lk.ijse.groceryshop.dto.OrdersDTO;
import lk.ijse.groceryshop.service.custom.OrderService;
import lk.ijse.groceryshop.service.util.Convertor;
import lk.ijse.groceryshop.util.HbFactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;

public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    private final Convertor convertor;
    private final Connection connection;
    private Session session;
    private Transaction transaction;

    public OrderServiceImpl(){
        connection= DBConnection.getDbcConnection().getConnection();
        orderDAO= DAOFactory.getInstance().getDAO(connection, DAOTypes.ORDER);
        convertor=new Convertor();
    }

    @Override
    public String getLastorderID() {
        session = HbFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();

        String id="null";
        try {
            id=orderDAO.getLastorderID(session);
            transaction.commit();
        } catch (HibernateException e) {
            if (session != null)
                id="null";
                transaction.rollback();
        } finally {
            session.close();
        }

        return id;
    }
}
