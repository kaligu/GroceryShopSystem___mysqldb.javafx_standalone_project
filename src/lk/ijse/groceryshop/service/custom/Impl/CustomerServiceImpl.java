package lk.ijse.groceryshop.service.custom.Impl;

import lk.ijse.groceryshop.dao.DAOFactory;
import lk.ijse.groceryshop.dao.DAOTypes;
import lk.ijse.groceryshop.dao.custom.CustomerDAO;
import lk.ijse.groceryshop.db.DBConnection;
import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.service.custom.CustomerService;
import lk.ijse.groceryshop.service.util.Convertor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;
    private final Convertor convertor;
    private final Connection connection;

    public CustomerServiceImpl(){
        connection= DBConnection.getDbcConnection().getConnection();
        customerDAO= DAOFactory.getInstance().getDAO(connection, DAOTypes.CUSTOMER);
        convertor=new Convertor();
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        if(customerDAO.existByPk(customerDTO.getId())) {
            customerDAO.save(convertor.toCustomer(customerDTO));
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        if(customerDAO.existByPk(customerDTO.getId())) {
            customerDAO.update(convertor.toCustomer(customerDTO));
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(String pk) {

        customerDAO.deleteByPk(pk);

        return true;
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
        List<CustomerDTO> list=new ArrayList<>();
        List<Customer> listc = new ArrayList<>();
        listc.addAll(customerDAO.SearchCustomersByTesxt(text));
        for(Customer c:listc){
            list.add(convertor.fromCustomer(c));
        }
        return  list;
    }
}
