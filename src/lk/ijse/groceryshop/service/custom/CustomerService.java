package lk.ijse.groceryshop.service.custom;

import lk.ijse.groceryshop.dto.CustomerDTO;
import lk.ijse.groceryshop.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {
    public void saveCustomer(CustomerDTO customerDTO);

    public void updateCustomer(CustomerDTO customerDTO);

    public boolean deleteCustomer(String pk);

    public List<CustomerDTO> findAllCustomers();

    public CustomerDTO findCustomerByPk(String pk);

    public List<CustomerDTO> searchCustomerByText(String text);

}
