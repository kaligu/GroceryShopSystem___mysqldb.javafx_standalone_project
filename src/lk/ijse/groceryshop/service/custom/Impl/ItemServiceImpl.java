package lk.ijse.groceryshop.service.custom.Impl;

import lk.ijse.groceryshop.dao.DAOFactory;
import lk.ijse.groceryshop.dao.DAOTypes;
import lk.ijse.groceryshop.dao.custom.ItemDAO;
import lk.ijse.groceryshop.db.DBConnection;
import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.dto.ItemDTO;
import lk.ijse.groceryshop.entity.Item;
import lk.ijse.groceryshop.service.custom.ItemService;
import lk.ijse.groceryshop.service.util.Convertor;
import lk.ijse.groceryshop.util.HbFactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemServiceImpl implements ItemService {
    private final ItemDAO itemDAO;
    private final Convertor convertor;
    private final Connection connection;
    private Session session;
    private Transaction transaction;

    public ItemServiceImpl(){
        connection= DBConnection.getDbcConnection().getConnection();
        itemDAO= DAOFactory.getInstance().getDAO(connection, DAOTypes.ITEM);
        convertor=new Convertor();
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO) {
        session= HbFactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        if(itemDAO.existByPk(itemDTO.getCode())) {
            try {
                itemDAO.save(convertor.toItem(itemDTO) , session);
                transaction.commit();
                return true;
            } catch (HibernateException e) {
                if (session!=null)
                    transaction.rollback();
                return false;
            } finally {
                session.close();
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) {
        session= HbFactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        if(true) {
            try {
                itemDAO.update(convertor.toItem(itemDTO) , session);
                transaction.commit();
                return true;
            } catch (HibernateException e) {
                if (session!=null)
                    transaction.rollback();
                return false;
            } finally {
                session.close();
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteItem(String pk) {
        session= HbFactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        if(itemDAO.existByPk(pk)){
            try {
                itemDAO.deleteByPk(pk,session);
                transaction.commit();
                return true;
            } catch (HibernateException e) {
                if (session!=null)
                    transaction.rollback();
                return false;
            } finally {
                session.close();
            }
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
        session= HbFactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();

        List<ItemDTO> itemDTOList = new ArrayList<>();

        try {

            itemDTOList = itemDAO.SearchItemsByTesxt(text,session).stream().map(item -> convertor.fromItem(item)).collect(Collectors.toList());

            transaction.commit();
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return itemDTOList;

    }

    @Override
    public List<String> SearchItemAllIds() {
        session= HbFactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();

        try{
            return itemDAO.SearchItemAllIds(session);
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }
}
