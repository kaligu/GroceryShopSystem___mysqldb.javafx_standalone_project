package lk.ijse.groceryshop.dao.custom;

import lk.ijse.groceryshop.dao.CrudDAO;
import lk.ijse.groceryshop.entity.Item;
import org.hibernate.Session;

import java.util.Collection;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String> {
    Collection<? extends Item> SearchItemsByTesxt(String text , Session session);

    List<String> SearchItemAllIds(Session session);
}
