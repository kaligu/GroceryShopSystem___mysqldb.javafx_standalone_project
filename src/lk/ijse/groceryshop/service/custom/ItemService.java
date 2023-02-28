package lk.ijse.groceryshop.service.custom;

import lk.ijse.groceryshop.dao.CrudDAO;
import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.dto.ItemDTO;
import lk.ijse.groceryshop.service.SuperService;

import java.util.List;

public interface ItemService extends SuperService {
    public boolean saveItem(ItemDTO itemDTO);

    public boolean updateItem(ItemDTO itemDTO);

    public boolean deleteItem(String pk);

    public List<ItemDTO> findAllItems();

    public ItemDTO findCustomerByPk(String pk);

    public List<ItemDTO> searchCustomerByText(String text);
}
