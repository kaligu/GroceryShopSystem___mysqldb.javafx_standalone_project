package lk.ijse.groceryshop.service.custom.Impl;

import lk.ijse.groceryshop.dao.DAOFactory;
import lk.ijse.groceryshop.dao.DAOTypes;
import lk.ijse.groceryshop.dao.custom.CustomerDAO;
import lk.ijse.groceryshop.dao.custom.ItemDAO;
import lk.ijse.groceryshop.db.DBConnection;
import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.dto.ItemDTO;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.entity.Item;
import lk.ijse.groceryshop.service.custom.ItemService;
import lk.ijse.groceryshop.service.util.Convertor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    private final ItemDAO itemDAO;
    private final Convertor convertor;
    private final Connection connection;

    public ItemServiceImpl(){
        connection= DBConnection.getDbcConnection().getConnection();
        itemDAO= DAOFactory.getInstance().getDAO(connection, DAOTypes.ITEM);
        convertor=new Convertor();
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO) {
        if(itemDAO.existByPk(itemDTO.getCode())) {
           // itemDAO.save(convertor.toItem(itemDTO));
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) {
        if(itemDAO.existByPk(itemDTO.getCode())) {
           // itemDAO.update(convertor.toItem(itemDTO));
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteItem(String pk) {
        if(itemDAO.existByPk(pk)){
            itemDAO.deleteByPk(pk);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<ItemDTO> findAllItems() {
        return null;
    }

    @Override
    public ItemDTO findCustomerByPk(String pk) {
        return null;
    }

    @Override
    public List<ItemDTO> searchCustomerByText(String text) {
        List<ItemDTO> list=new ArrayList<>();
        List<Item> listc = new ArrayList<>();
        listc.addAll(itemDAO.SearchItemsByTesxt(text));
        for(Item c:listc){
           // list.add(convertor.fromItem(c));
        }
        return  list;
    }
}
