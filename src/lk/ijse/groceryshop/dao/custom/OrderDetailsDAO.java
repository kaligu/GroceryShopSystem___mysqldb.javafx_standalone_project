package lk.ijse.groceryshop.dao.custom;

import lk.ijse.groceryshop.dao.CrudDAO;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.entity.OrderDetails;
import org.hibernate.Session;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails,String> {
    OrderDetails saveAndGet(OrderDetails od, Session session);
}
