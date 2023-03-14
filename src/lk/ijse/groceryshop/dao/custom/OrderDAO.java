package lk.ijse.groceryshop.dao.custom;

import lk.ijse.groceryshop.dao.CrudDAO;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.entity.Order;
import org.hibernate.Session;

public interface OrderDAO extends CrudDAO<Order,String> {
    String getLastorderID(Session session);
}
