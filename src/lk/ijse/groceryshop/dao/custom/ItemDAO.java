package lk.ijse.groceryshop.dao.custom;

import lk.ijse.groceryshop.dao.CrudDAO;
import lk.ijse.groceryshop.entity.Item;

import java.util.Collection;

public interface ItemDAO extends CrudDAO<Item,String> {
    Collection<? extends Item> SearchItemsByTesxt(String text);
}
