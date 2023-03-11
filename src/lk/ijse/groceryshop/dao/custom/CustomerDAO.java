package lk.ijse.groceryshop.dao.custom;

import lk.ijse.groceryshop.dao.CrudDAO;
import lk.ijse.groceryshop.entity.Customer;
import org.hibernate.Session;

import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    List<Customer> SearchCustomersByTesxt(String text, Session session);

    List<String> SearchCustomerAllIds(Session session);
}
