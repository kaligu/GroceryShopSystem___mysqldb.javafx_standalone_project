package lk.ijse.groceryshop.service.custom.Impl;

import lk.ijse.groceryshop.dao.DAOFactory;
import lk.ijse.groceryshop.dao.DAOTypes;
import lk.ijse.groceryshop.dao.custom.CustomerDAO;
import lk.ijse.groceryshop.db.DBConnection;
import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.service.custom.CustomerService;
import lk.ijse.groceryshop.service.util.Convertor;
import lk.ijse.groceryshop.util.HbFactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;
    private final Convertor convertor;
    private final Connection connection;
    private Session session;
    private Transaction transaction;


    public CustomerServiceImpl(){
        connection= DBConnection.getDbcConnection().getConnection();
        customerDAO= DAOFactory.getInstance().getDAO(connection, DAOTypes.CUSTOMER);
        convertor=new Convertor();
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        session= HbFactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        if(!customerDAO.existByPk(customerDTO.getId())) {

            try {
                customerDAO.save(convertor.toCustomer(customerDTO) , session);
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
    public boolean updateCustomer(CustomerDTO customerDTO) {
        session= HbFactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        try {
            customerDAO.update(convertor.toCustomer(customerDTO),session);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteCustomer(String pk) {
        session= HbFactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        try {
            customerDAO.deleteByPk(pk,session);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return null;
    }

    @Override
    public CustomerDTO findCustomerByPk(String pk) {
        return null;
    }

    @Override
    public List<CustomerDTO> searchCustomerByText(String text) {
        session= HbFactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        try {

            customerDTOList= customerDAO.SearchCustomersByTesxt(text,session).stream().map(customer -> convertor.fromCustomer(customer)).collect(Collectors.toList());

            transaction.commit();
        } catch (HibernateException e) {
            if (session!=null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return customerDTOList;
    }
}
